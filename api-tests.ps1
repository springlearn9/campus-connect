$base = 'http://localhost:8080'

function Try-Req($method, $uri, $body = $null) {
    Write-Host "\n=== $method $uri ==="
    try {
        if ($body -ne $null) {
            $json = $body | ConvertTo-Json -Depth 5
            $resp = Invoke-RestMethod -Method $method -Uri $uri -Body $json -ContentType 'application/json' -ErrorAction Stop
            Write-Host ("Response:")
            $resp | ConvertTo-Json -Depth 5
        } else {
            $resp = Invoke-RestMethod -Method $method -Uri $uri -ErrorAction Stop
            Write-Host ("Response:")
            $resp | ConvertTo-Json -Depth 5
        }
    } catch {
        Write-Host "ERROR: $($_.Exception.Message)"
        if ($_.Exception.Response) {
            $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
            $body = $reader.ReadToEnd()
            Write-Host "Response body: $body"
        }
    }
}

# Wait a moment for the app to be ready
Write-Host "Waiting 2s for application to be ready..."
Start-Sleep -Seconds 2

# 1) Lost & Found - GET
Try-Req GET "$base/api/lost-items"
Try-Req GET "$base/api/found-items"

# 2) Lost & Found - POST (create) - userId=1
$lost = @{ itemName = 'Test Wallet'; description = 'Black leather wallet'; location = 'Library Lobby'; status = 'PENDING' }
Try-Req POST "$base/api/lost-items?userId=1" $lost

$found = @{ itemName = 'Blue Umbrella'; description = 'Blue umbrella with wooden handle'; location = 'Cafeteria'; photoUrl = ''; }
Try-Req POST "$base/api/found-items?userId=2" $found

# 3) Events - GET
Try-Req GET "$base/api/events"

# 4) Events - POST (create)
$event = @{ title = 'Test Meetup'; description = 'Testing event creation'; eventDate = (Get-Date).AddDays(3).ToString('yyyy-MM-ddTHH:mm:ss'); location = 'Test Hall'; imageUrl = ''; status = 'ACTIVE' }
Try-Req POST "$base/api/events?userId=2" $event

# 5) Notices - GET
Try-Req GET "$base/api/notices"

# 6) Notices - POST (create)
$notice = @{ title = 'Test Notice'; description = 'Notice created by automated test'; priority = 'NORMAL'; category = 'GENERAL'; validUntil = (Get-Date).AddDays(10).ToString('yyyy-MM-ddTHH:mm:ss'); status = 'ACTIVE'; attachmentUrl = '' }
Try-Req POST "$base/api/notices?userId=3" $notice

# 7) Quick checks: fetch created resources by listing again
Try-Req GET "$base/api/lost-items"
Try-Req GET "$base/api/found-items"
Try-Req GET "$base/api/events"
Try-Req GET "$base/api/notices"

Write-Host "\nAPI tests finished."