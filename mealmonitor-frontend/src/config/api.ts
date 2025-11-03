// API Configuration
// Use empty string for relative URLs (works with nginx proxy in Docker)
// For Kubernetes: Set REACT_APP_API_URL=http://localhost:31800 during build if needed
export const API_BASE_URL = process.env.REACT_APP_API_URL || '';

// API Endpoints
export const API_ENDPOINTS = {
  // User Service
  USERS_LOGIN: `${API_BASE_URL}/api/users/login`,
  USERS_REGISTER: `${API_BASE_URL}/api/users/register`,
  USERS_PROFILE: `${API_BASE_URL}/api/users/profile`,
  
  // Review Service
  REVIEWS: `${API_BASE_URL}/api/reviews`,
  REVIEWS_RECENT: `${API_BASE_URL}/api/reviews/recent`,
  
  // Canteen Service
  CANTEEN_ITEMS: `${API_BASE_URL}/api/canteen/items`,
  
  // Notification Service
  NOTIFICATIONS: `${API_BASE_URL}/api/notifications`,
  NOTIFICATIONS_READ_ALL: `${API_BASE_URL}/api/notifications/read-all`,
  
  // Poll Service
  POLLS: `${API_BASE_URL}/api/polls`,
  POLLS_ACTIVE: `${API_BASE_URL}/api/polls/active`,
  
  // Moderation Service
  MODERATION_FLAGS: `${API_BASE_URL}/api/moderation/flags`,
  MODERATION_ISSUES: `${API_BASE_URL}/api/moderation/issues`,
  MODERATION_LOGS: `${API_BASE_URL}/api/moderation/logs`,
};

// Helper function to get notification read endpoint
export const getNotificationReadUrl = (notificationId: string) => 
  `${API_BASE_URL}/api/notifications/${notificationId}/read`;

// Helper function to get poll vote endpoint
export const getPollVoteUrl = (pollId: string) => 
  `${API_BASE_URL}/api/polls/${pollId}/vote`;

// Helper function to get moderation issue resolution endpoint
export const getModerationResolutionUrl = (issueId: string) => 
  `${API_BASE_URL}/api/moderation/issues/${issueId}/resolutions`;




