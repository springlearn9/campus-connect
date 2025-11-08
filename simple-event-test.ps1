# Simple Event API Test - Campus Connect
Write-Host "=== Testing Event APIs on Port 8081 ===" -ForegroundColor Green

$baseUrl = "http://localhost:8081/api/events"

# Wait for app to be ready
Start-Sleep 3

# Test 1: Get all events
Write-Host "`nTesting GET all events..." -ForegroundColor Yellow
try {
    $events = Invoke-RestMethod -Uri $baseUrl -Method GET
    Write-Host "✅ SUCCESS - Found $($events.Count) events" -ForegroundColor Green
    $events | ConvertTo-Json -Depth 2 | Write-Host
} catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 2: Get upcoming events  
Write-Host "`nTesting GET upcoming events..." -ForegroundColor Yellow
try {
    $upcoming = Invoke-RestMethod -Uri "$baseUrl/upcoming" -Method GET
    Write-Host "✅ SUCCESS - Found $($upcoming.Count) upcoming events" -ForegroundColor Green
    $upcoming | ConvertTo-Json -Depth 2 | Write-Host
} catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 3: Search events
Write-Host "`nTesting search events by 'fest'..." -ForegroundColor Yellow
try {
    $search = Invoke-RestMethod -Uri "$baseUrl/search?keyword=fest" -Method GET
    Write-Host "✅ SUCCESS - Found $($search.Count) events matching 'fest'" -ForegroundColor Green
    $search | ConvertTo-Json -Depth 2 | Write-Host
} catch {
    Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n=== Event API Test Complete ===" -ForegroundColor Green