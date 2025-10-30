import React, { useState, useEffect } from 'react';
import {
  Container,
  Typography,
  Card,
  CardContent,
  Box,
  Chip,
  Rating,
  Alert,
} from '@mui/material';
import {
  Restaurant,
  RateReview,
  Notifications,
  TrendingUp,
} from '@mui/icons-material';
import axios from 'axios';
import { API_ENDPOINTS } from '../config/api';

interface Review {
  id: string;
  itemName: string;
  rating: number;
  comment: string;
  createdAt: string;
}

interface CanteenItem {
  id: string;
  name: string;
  description: string;
  price: number;
  category: string;
  available: boolean;
}

const Dashboard: React.FC = () => {
  const [recentReviews, setRecentReviews] = useState<Review[]>([]);
  const [canteenItems, setCanteenItems] = useState<CanteenItem[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const fetchDashboardData = async () => {
    try {
      const token = localStorage.getItem('token');
      const headers = { Authorization: `Bearer ${token}` };

      const [reviewsResponse, canteenResponse] = await Promise.all([
        axios.get(API_ENDPOINTS.REVIEWS_RECENT, { headers }),
        axios.get(API_ENDPOINTS.CANTEEN_ITEMS, { headers }),
      ]);

      setRecentReviews(reviewsResponse.data);
      setCanteenItems(canteenResponse.data.slice(0, 6));
    } catch (err: any) {
      setError('Failed to load dashboard data');
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <Container>
        <Typography>Loading...</Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg">
      <Box sx={{ py: 4 }}>
        <Typography variant="h4" component="h1" gutterBottom>
          Dashboard
        </Typography>

        {error && (
          <Alert severity="error" sx={{ mb: 3 }}>
            {error}
          </Alert>
        )}

        <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', md: '1fr 1fr 1fr 1fr' }, gap: 3, mb: 3 }}>
          {/* Quick Stats */}
          <Card>
            <CardContent>
              <Box display="flex" alignItems="center">
                <Restaurant color="primary" sx={{ mr: 1 }} />
                <Typography variant="h6">Canteen Items</Typography>
              </Box>
              <Typography variant="h4">{canteenItems.length}</Typography>
            </CardContent>
          </Card>

          <Card>
            <CardContent>
              <Box display="flex" alignItems="center">
                <RateReview color="primary" sx={{ mr: 1 }} />
                <Typography variant="h6">Reviews</Typography>
              </Box>
              <Typography variant="h4">{recentReviews.length}</Typography>
            </CardContent>
          </Card>

          <Card>
            <CardContent>
              <Box display="flex" alignItems="center">
                <Notifications color="primary" sx={{ mr: 1 }} />
                <Typography variant="h6">Notifications</Typography>
              </Box>
              <Typography variant="h4">0</Typography>
            </CardContent>
          </Card>

          <Card>
            <CardContent>
              <Box display="flex" alignItems="center">
                <TrendingUp color="primary" sx={{ mr: 1 }} />
                <Typography variant="h6">Activity</Typography>
              </Box>
              <Typography variant="h4">High</Typography>
            </CardContent>
          </Card>
        </Box>

        <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', md: '1fr 1fr' }, gap: 3 }}>
          {/* Recent Reviews */}
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Recent Reviews
              </Typography>
              {recentReviews.length === 0 ? (
                <Typography color="text.secondary">
                  No recent reviews found.
                </Typography>
              ) : (
                recentReviews.map((review) => (
                  <Box key={review.id} sx={{ mb: 2, p: 2, border: 1, borderColor: 'divider', borderRadius: 1 }}>
                    <Typography variant="subtitle1" fontWeight="bold">
                      {review.itemName}
                    </Typography>
                    <Rating value={review.rating} readOnly size="small" />
                    <Typography variant="body2" color="text.secondary">
                      {review.comment}
                    </Typography>
                    <Typography variant="caption" color="text.secondary">
                      {new Date(review.createdAt).toLocaleDateString()}
                    </Typography>
                  </Box>
                ))
              )}
            </CardContent>
          </Card>

          {/* Canteen Items */}
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Available Items
              </Typography>
              <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', sm: '1fr 1fr' }, gap: 2 }}>
                {canteenItems.map((item) => (
                  <Card key={item.id} variant="outlined">
                    <CardContent>
                      <Typography variant="subtitle1" fontWeight="bold">
                        {item.name}
                      </Typography>
                      <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                        {item.description}
                      </Typography>
                      <Box display="flex" justifyContent="space-between" alignItems="center">
                        <Chip
                          label={item.category}
                          size="small"
                          color="primary"
                          variant="outlined"
                        />
                        <Typography variant="h6" color="primary">
                          ${item.price}
                        </Typography>
                      </Box>
                      <Chip
                        label={item.available ? 'Available' : 'Unavailable'}
                        size="small"
                        color={item.available ? 'success' : 'error'}
                        sx={{ mt: 1 }}
                      />
                    </CardContent>
                  </Card>
                ))}
              </Box>
            </CardContent>
          </Card>
        </Box>
      </Box>
    </Container>
  );
};

export default Dashboard;
