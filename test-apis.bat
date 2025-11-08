# Campus Connect API Testing Script
# Run this script to test all APIs

echo "🚀 Campus Connect API Testing Started..."
echo "=========================================="

# Wait for application to start
echo "⏳ Waiting for application to start..."
timeout /t 5 /nobreak > nul

# Test 1: Users API
echo ""
echo "📋 Testing Users API..."
echo "1. GET /api/users - Get all users"
curl -s -X GET http://localhost:8080/api/users | echo "Response received"

echo ""
echo "2. GET /api/users/1 - Get user by ID"
curl -s -X GET http://localhost:8080/api/users/1 | echo "Response received"

# Test 2: Lost Items API
echo ""
echo "🔍 Testing Lost Items API..."
echo "3. GET /api/lost-items - Get all lost items"
curl -s -X GET http://localhost:8080/api/lost-items | echo "Response received"

echo ""
echo "4. GET /api/lost-items/1 - Get lost item by ID"
curl -s -X GET http://localhost:8080/api/lost-items/1 | echo "Response received"

# Test 3: Found Items API
echo ""
echo "🎯 Testing Found Items API..."
echo "5. GET /api/found-items - Get all found items"
curl -s -X GET http://localhost:8080/api/found-items | echo "Response received"

echo ""
echo "6. GET /api/found-items/1 - Get found item by ID"
curl -s -X GET http://localhost:8080/api/found-items/1 | echo "Response received"

# Test 4: Events API
echo ""
echo "📅 Testing Events API..."
echo "7. GET /api/events - Get all events"
curl -s -X GET http://localhost:8080/api/events | echo "Response received"

echo ""
echo "8. GET /api/events/1 - Get event by ID"
curl -s -X GET http://localhost:8080/api/events/1 | echo "Response received"

echo ""
echo "9. GET /api/events/upcoming - Get upcoming events"
curl -s -X GET http://localhost:8080/api/events/upcoming | echo "Response received"

echo ""
echo "10. GET /api/events/status/ACTIVE - Get active events"
curl -s -X GET http://localhost:8080/api/events/status/ACTIVE | echo "Response received"

# Test 5: Notices API
echo ""
echo "📢 Testing Notices API..."
echo "11. GET /api/notices - Get all notices"
curl -s -X GET http://localhost:8080/api/notices | echo "Response received"

echo ""
echo "12. GET /api/notices/1 - Get notice by ID"
curl -s -X GET http://localhost:8080/api/notices/1 | echo "Response received"

echo ""
echo "13. GET /api/notices/active - Get active notices"
curl -s -X GET http://localhost:8080/api/notices/active | echo "Response received"

echo ""
echo "14. GET /api/notices/priority/HIGH - Get high priority notices"
curl -s -X GET http://localhost:8080/api/notices/priority/HIGH | echo "Response received"

echo ""
echo "15. GET /api/notices/category/ACADEMIC - Get academic notices"
curl -s -X GET http://localhost:8080/api/notices/category/ACADEMIC | echo "Response received"

# Test 6: Search APIs
echo ""
echo "🔎 Testing Search APIs..."
echo "16. Search events by keyword"
curl -s -X GET "http://localhost:8080/api/events/search?keyword=Tech" | echo "Response received"

echo ""
echo "17. Search notices by keyword"
curl -s -X GET "http://localhost:8080/api/notices/search?keyword=Exam" | echo "Response received"

echo ""
echo "✅ API Testing Completed!"
echo "Check the responses above to verify all APIs are working correctly."