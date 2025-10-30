-- Create all required databases for MealMonitor microservices
CREATE DATABASE IF NOT EXISTS mealmontor_users;
CREATE DATABASE IF NOT EXISTS mealmontor_canteen;
CREATE DATABASE IF NOT EXISTS mealmontor_reviews;
CREATE DATABASE IF NOT EXISTS mealmontor_notifications;
CREATE DATABASE IF NOT EXISTS mealmontor_polls;
--CREATE DATABASE IF NOT EXISTS mealmontor_moderation;

-- Grant all privileges to root user on all databases
GRANT ALL PRIVILEGES ON mealmontor_users.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON mealmontor_canteen.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON mealmontor_reviews.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON mealmontor_notifications.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON mealmontor_polls.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON mealmontor_moderation.* TO 'root'@'%';

FLUSH PRIVILEGES;



