import React, { useState, useEffect } from 'react';
import {
  Container,
  Typography,
  Card,
  CardContent,
  Rating,
  TextField,
  Button,
  Box,
  Chip,
  Alert,
  CircularProgress,
} from '@mui/material';
import { Send } from '@mui/icons-material';
import axios from 'axios';
import { API_ENDPOINTS } from '../config/api';
 
interface Review {
  reviewId: number;
  itemName: string;
  rating: number;
  comment: string;
  createdAt: string;
  user: {
    firstName: string;
    lastName: string;
  };
}
 
const Reviews: React.FC = () => {
  const [reviews, setReviews] = useState<Review[]>([]);
  const [newReview, setNewReview] = useState({
    itemName: '',
    rating: 0,
    comment: '',
  });
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');
 
  useEffect(() => {
    fetchReviews();
  }, []);
 
  const fetchReviews = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get(API_ENDPOINTS.REVIEWS, {
        headers: { Authorization: `Bearer ${token}` },
      });

      // Map backend response to frontend interface
      const mappedReviews: Review[] = response.data.map((r: any) => ({
        reviewId: r.reviewId,
        itemName: r.dishId, // backend field mapped to frontend
        rating: r.rating,
        comment: r.comment,
        createdAt: r.createdAt,
        user: {
          firstName: `User ${r.userId}`, // placeholder
          lastName: '',
        },
      }));

      setReviews(mappedReviews);
    } catch (err) {
      setError('Failed to load reviews');
    } finally {
      setLoading(false);
    }
  };
 
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setSubmitting(true);
    setError('');
 
    try {
      const token = localStorage.getItem('token');
 
      // Convert frontend review to backend expected shape
      const payload = {
        dishId: newReview.itemName,
        rating: newReview.rating,
        comment: newReview.comment,
        userId: '10', // Replace with logged-in userId if available
      };

      await axios.post(API_ENDPOINTS.REVIEWS, payload, {
        headers: { Authorization: `Bearer ${token}` },
      });
 
      setNewReview({ itemName: '', rating: 0, comment: '' });
      fetchReviews();
    } catch (err) {
      setError('Failed to submit review');
    } finally {
      setSubmitting(false);
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
 
  return (
    <Container maxWidth="lg">
      <Box sx={{ py: 4 }}>
        <Typography variant="h4" component="h1" gutterBottom>
          Reviews
        </Typography>
 
        {error && (
          <Alert severity="error" sx={{ mb: 3 }}>
            {error}
          </Alert>
        )}
 
        {/* Add Review Form */}
        <Card sx={{ mb: 4 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Add a Review
            </Typography>
            <Box component="form" onSubmit={handleSubmit}>
              <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', sm: '1fr 1fr' }, gap: 2 }}>
                <Box>
                  <TextField
                    fullWidth
                    label="Item Name"
                    value={newReview.itemName}
                    onChange={(e) => setNewReview({ ...newReview, itemName: e.target.value })}
                    required
                  />
                </Box>
                <Box>
                  <Box display="flex" alignItems="center" gap={1}>
                    <Typography>Rating:</Typography>
                    <Rating
                      value={newReview.rating}
                      onChange={(_, value) => setNewReview({ ...newReview, rating: value || 0 })}
                    />
                  </Box>
                </Box>
                <Box sx={{ gridColumn: { xs: '1', sm: '1 / -1' } }}>
                  <TextField
                    fullWidth
                    multiline
                    rows={3}
                    label="Comment"
                    value={newReview.comment}
                    onChange={(e) => setNewReview({ ...newReview, comment: e.target.value })}
                    required
                  />
                </Box>
                <Box sx={{ gridColumn: { xs: '1', sm: '1 / -1' } }}>
                  <Button
                    type="submit"
                    variant="contained"
                    startIcon={<Send />}
                    disabled={submitting}
                  >
                    {submitting ? <CircularProgress size={24} /> : 'Submit Review'}
                  </Button>
                </Box>
              </Box>
            </Box>
          </CardContent>
        </Card>
 
        {/* Reviews List */}
        <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', md: '1fr 1fr' }, gap: 3 }}>
          {reviews.length === 0 ? (
            <Box sx={{ gridColumn: '1 / -1' }}>
              <Typography color="text.secondary" align="center">
                No reviews found.
              </Typography>
            </Box>
          ) : (
            reviews.map((review) => (
              <Card key={review.reviewId}>
                <CardContent>
                  <Box display="flex" justifyContent="space-between" alignItems="flex-start" mb={1}>
                    <Typography variant="h6" component="h3">
                      {review.itemName}
                    </Typography>
                    <Chip label={review.rating} color="primary" size="small" />
                  </Box>
                  <Rating value={review.rating} readOnly size="small" sx={{ mb: 1 }} />
                  <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                    by {review.user.firstName} {review.user.lastName}
                  </Typography>
                  <Typography variant="body1" sx={{ mb: 1 }}>
                    {review.comment}
                  </Typography>
                  <Typography variant="caption" color="text.secondary">
                    {new Date(review.createdAt).toLocaleDateString()}
                  </Typography>
                </CardContent>
              </Card>
            ))
          )}
        </Box>
      </Box>
    </Container>
  );
};
 
export default Reviews;
 
 