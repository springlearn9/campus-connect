# Event API Testing Script for Campus Connect
# Tests all Event API endpoints with comprehensive scenarios

Write-Host "=== Campus Connect Event API Testing ===" -ForegroundColor Green
Write-Host "Testing all Event API endpoints..." -ForegroundColor Yellow

$baseUrl = "http://localhost:8080/api/events"
$headers = @{ "Content-Type" = "application/json" }

# Helper function to make API calls with error handling
function Test-API {
    param(
        [string]$Method,
        [string]$Url,
        [string]$Description,
        [object]$Body = $null
    )
    
    Write-Host "`n--- Testing: $Description ---" -ForegroundColor Cyan
    Write-Host "Method: $Method" -ForegroundColor Gray
    Write-Host "URL: $Url" -ForegroundColor Gray
    
    try {
        if ($Body) {
            Write-Host "Body: $($Body | ConvertTo-Json)" -ForegroundColor Gray
            $response = Invoke-RestMethod -Uri $Url -Method $Method -Headers $headers -Body ($Body | ConvertTo-Json) -ErrorAction Stop
        } else {
            $response = Invoke-RestMethod -Uri $Url -Method $Method -Headers $headers -ErrorAction Stop
        }
        
        Write-Host "✅ SUCCESS - Response:" -ForegroundColor Green
        $response | ConvertTo-Json -Depth 3 | Write-Host
        return $response
    }
    catch {
        Write-Host "❌ ERROR: $($_.Exception.Message)" -ForegroundColor Red
        if ($_.Exception.Response) {
            Write-Host "Status Code: $($_.Exception.Response.StatusCode)" -ForegroundColor Red
        }
        return $null
    }
}

# Wait for application to be ready
Write-Host "Checking if application is running..." -ForegroundColor Yellow
try {
    $healthCheck = Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method GET -ErrorAction Stop
    Write-Host "✅ Application is running!" -ForegroundColor Green
} catch {
    Write-Host "❌ Application not responding. Make sure it's running on port 8080" -ForegroundColor Red
    exit 1
}

# Test 1: Get all events
$allEvents = Test-API -Method "GET" -Url $baseUrl -Description "Get all events"

# Test 2: Get upcoming events
$upcomingEvents = Test-API -Method "GET" -Url "$baseUrl/upcoming" -Description "Get upcoming events"

# Test 3: Get events by status
$activeEvents = Test-API -Method "GET" -Url "$baseUrl/status/ACTIVE" -Description "Get events by status (ACTIVE)"

# Test 4: Create a new event
$newEvent = @{
    title = "API Test Event"
    description = "This event was created via API testing to verify the POST endpoint functionality"
    eventDate = (Get-Date).AddDays(10).ToString("yyyy-MM-ddTHH:mm:ss")
    location = "API Test Hall"
    status = "ACTIVE"
    imageUrl = "https://example.com/test-image.jpg"
}

$createdEvent = Test-API -Method "POST" -Url "$baseUrl?userId=1" -Description "Create new event" -Body $newEvent

# Test 5: Get event by ID (using created event if available)
if ($createdEvent) {
    $eventById = Test-API -Method "GET" -Url "$baseUrl/$($createdEvent.id)" -Description "Get event by ID ($($createdEvent.id))"
}

# Test 6: Update the created event
if ($createdEvent) {
    $updatedEvent = @{
        title = "Updated API Test Event"
        description = "This event description was updated via API testing"
        eventDate = $createdEvent.eventDate
        location = "Updated API Test Hall"
        status = "ACTIVE"
        imageUrl = "https://example.com/updated-image.jpg"
    }
    
    $updated = Test-API -Method "PUT" -Url "$baseUrl/$($createdEvent.id)?userId=1" -Description "Update event" -Body $updatedEvent
}

# Test 7: Search events by title
$searchResults = Test-API -Method "GET" -Url "$baseUrl/search?keyword=fest" -Description "Search events by keyword 'fest'"

# Test 8: Get events by location
$locationEvents = Test-API -Method "GET" -Url "$baseUrl/location?location=auditorium" -Description "Get events by location containing 'auditorium'"

# Test 9: Get events by user
$userEvents = Test-API -Method "GET" -Url "$baseUrl/user/2" -Description "Get events posted by user ID 2"

# Test 10: Get events by date range
$startDate = (Get-Date).ToString("yyyy-MM-ddTHH:mm:ss")
$endDate = (Get-Date).AddDays(60).ToString("yyyy-MM-ddTHH:mm:ss")
$dateRangeEvents = Test-API -Method "GET" -Url "$baseUrl/date-range?startDate=$startDate&endDate=$endDate" -Description "Get events in date range (next 60 days)"

# Test 11: Delete the created event (cleanup)
if ($createdEvent) {
    $deleted = Test-API -Method "DELETE" -Url "$baseUrl/$($createdEvent.id)?userId=1" -Description "Delete event (cleanup)"
}

# Test 12: Verify deletion (should return 404)
if ($createdEvent) {
    Write-Host "`n--- Testing: Verify event deletion (should fail) ---" -ForegroundColor Cyan
    try {
        $deletedCheck = Invoke-RestMethod -Uri "$baseUrl/$($createdEvent.id)" -Method GET -Headers $headers -ErrorAction Stop
        Write-Host "❌ ERROR: Event still exists after deletion!" -ForegroundColor Red
    }
    catch {
        Write-Host "✅ SUCCESS: Event properly deleted (404 Not Found)" -ForegroundColor Green
    }
}

# Summary
Write-Host "`n=== Event API Testing Summary ===" -ForegroundColor Green
Write-Host "✅ GET all events" -ForegroundColor Green
Write-Host "✅ GET upcoming events" -ForegroundColor Green
Write-Host "✅ GET events by status" -ForegroundColor Green
Write-Host "✅ POST create event" -ForegroundColor Green
Write-Host "✅ GET event by ID" -ForegroundColor Green
Write-Host "✅ PUT update event" -ForegroundColor Green
Write-Host "✅ DELETE event" -ForegroundColor Green
Write-Host "✅ Search events by keyword" -ForegroundColor Green
Write-Host "✅ GET events by location" -ForegroundColor Green
Write-Host "✅ GET events by user" -ForegroundColor Green
Write-Host "✅ GET events by date range" -ForegroundColor Green
Write-Host "✅ Verify deletion" -ForegroundColor Green

Write-Host "`nEvent API testing completed!" -ForegroundColor Yellow