# Campus Connect - Git Commands

## After installing Git and configuring it, run these commands:

# 1. Initialize repository (if not already done)
git init

# 2. Add all files to staging
git add .

# 3. Create initial commit
git commit -m "feat: Initial commit - Campus Connect Portal with comprehensive features

- Enhanced User Management with roles and profiles
- Advanced Lost & Found system with image upload
- Real-time notification system using WebSocket
- Event and Notice management with search/filter
- Complete Swagger API documentation
- File upload service with validation
- Email integration for notifications
- H2 database with proper entity relationships"

# 4. Add remote repository (replace with your actual repository URL)
git remote add origin https://github.com/springlearn9/campus-connect.git

# 5. Push to GitHub
git push -u origin main

## Alternative: If you want to create a new repository on GitHub
# Go to https://github.com/new
# Create a repository named 'campus-connect'
# Then use the URL provided by GitHub in step 4 above

## Check status at any time
git status

## View commit history
git log --oneline