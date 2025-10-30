import React, { useState, useEffect } from 'react';
import {
  Container,
  Typography,
  List,
  ListItemButton,
  ListItemText,
  ListItemIcon,
  Card,
  CardContent,
  Box,
  Chip,
  Alert,
  CircularProgress,
  Button,
} from '@mui/material';
import {
  Notifications as NotificationsIcon,
  Info,
  Warning,
  Error,
  CheckCircle,
} from '@mui/icons-material';
import axios from 'axios';
import { API_ENDPOINTS, getNotificationReadUrl } from '../config/api';

interface Notification {
  id: string;
  title: string;
  message: string;
  type: 'info' | 'warning' | 'error' | 'success';
  read: boolean;
  createdAt: string;
}

const Notifications: React.FC = () => {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchNotifications();
  }, []);

  const fetchNotifications = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get(API_ENDPOINTS.NOTIFICATIONS, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setNotifications(response.data);
    } catch (err: any) {
      setError('Failed to load notifications');
    } finally {
      setLoading(false);
    }
  };

  const markAsRead = async (notificationId: string) => {
    try {
      const token = localStorage.getItem('token');
      await axios.put(getNotificationReadUrl(notificationId), {}, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setNotifications(notifications.map(notif => 
        notif.id === notificationId ? { ...notif, read: true } : notif
      ));
    } catch (err: any) {
      setError('Failed to mark notification as read');
    }
  };

  const markAllAsRead = async () => {
    try {
      const token = localStorage.getItem('token');
      await axios.put(API_ENDPOINTS.NOTIFICATIONS_READ_ALL, {}, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setNotifications(notifications.map(notif => ({ ...notif, read: true })));
    } catch (err: any) {
      setError('Failed to mark all notifications as read');
    }
  };

  const getNotificationIcon = (type: string) => {
    switch (type) {
      case 'info':
        return <Info color="info" />;
      case 'warning':
        return <Warning color="warning" />;
      case 'error':
        return <Error color="error" />;
      case 'success':
        return <CheckCircle color="success" />;
      default:
        return <NotificationsIcon />;
    }
  };

  const getNotificationColor = (type: string) => {
    switch (type) {
      case 'info':
        return 'info';
      case 'warning':
        return 'warning';
      case 'error':
        return 'error';
      case 'success':
        return 'success';
      default:
        return 'default';
    }
  };

  if (loading) {
    return (
      <Container>
        <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
          <CircularProgress />
        </Box>
      </Container>
    );
  }

  const unreadCount = notifications.filter(notif => !notif.read).length;

  return (
    <Container maxWidth="md">
      <Box sx={{ py: 4 }}>
        <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
          <Typography variant="h4" component="h1">
            Notifications
          </Typography>
          {unreadCount > 0 && (
            <Button
              variant="outlined"
              onClick={markAllAsRead}
              disabled={loading}
            >
              Mark All as Read
            </Button>
          )}
        </Box>

        {error && (
          <Alert severity="error" sx={{ mb: 3 }}>
            {error}
          </Alert>
        )}

        {unreadCount > 0 && (
          <Alert severity="info" sx={{ mb: 3 }}>
            You have {unreadCount} unread notification{unreadCount > 1 ? 's' : ''}.
          </Alert>
        )}

        {notifications.length === 0 ? (
          <Card>
            <CardContent>
              <Box textAlign="center" py={4}>
                <NotificationsIcon sx={{ fontSize: 64, color: 'text.secondary', mb: 2 }} />
                <Typography variant="h6" color="text.secondary">
                  No notifications yet
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  You'll see notifications about canteen updates, new items, and reviews here.
                </Typography>
              </Box>
            </CardContent>
          </Card>
        ) : (
          <List>
            {notifications.map((notification) => (
              <Card
                key={notification.id}
                sx={{
                  mb: 2,
                  opacity: notification.read ? 0.7 : 1,
                  borderLeft: notification.read ? 'none' : '4px solid',
                  borderLeftColor: getNotificationColor(notification.type) + '.main',
                }}
              >
                <ListItemButton
                  onClick={() => !notification.read && markAsRead(notification.id)}
                >
                  <ListItemIcon>
                    {getNotificationIcon(notification.type)}
                  </ListItemIcon>
                  <ListItemText
                    primary={
                      <Box display="flex" alignItems="center" gap={1}>
                        <Typography variant="subtitle1" fontWeight={notification.read ? 'normal' : 'bold'}>
                          {notification.title}
                        </Typography>
                        {!notification.read && (
                          <Chip label="New" size="small" color="primary" />
                        )}
                      </Box>
                    }
                    secondary={
                      <Box>
                        <Typography variant="body2" color="text.secondary">
                          {notification.message}
                        </Typography>
                        <Typography variant="caption" color="text.secondary">
                          {new Date(notification.createdAt).toLocaleString()}
                        </Typography>
                      </Box>
                    }
                  />
                </ListItemButton>
              </Card>
            ))}
          </List>
        )}
      </Box>
    </Container>
  );
};

export default Notifications;
