import React from 'react';
import {
  Container,
  Typography,
  Box,
  Card,
  CardContent,
  Button,
  Paper,
} from '@mui/material';
import {
  Restaurant,
  RateReview,
  Notifications,
  People,
} from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';

const Home: React.FC = () => {
  const navigate = useNavigate();

  const features = [
    {
      icon: <Restaurant sx={{ fontSize: 40, color: 'primary.main' }} />,
      title: 'Canteen Management',
      description: 'Browse canteen items, view daily menus, and manage food offerings.',
    },
    {
      icon: <RateReview sx={{ fontSize: 40, color: 'primary.main' }} />,
      title: 'Review System',
      description: 'Rate and review food items, share your dining experience.',
    },
    {
      icon: <Notifications sx={{ fontSize: 40, color: 'primary.main' }} />,
      title: 'Real-time Notifications',
      description: 'Get notified about new items, reviews, and canteen updates.',
    },
    {
      icon: <People sx={{ fontSize: 40, color: 'primary.main' }} />,
      title: 'Community Driven',
      description: 'Join a community of students sharing their dining experiences.',
    },
  ];

  return (
    <Container maxWidth="lg">
      <Box sx={{ py: 8 }}>
        {/* Hero Section */}
        <Paper
          elevation={3}
          sx={{
            p: 6,
            mb: 6,
            background: 'linear-gradient(135deg, #1976d2 0%, #42a5f5 100%)',
            color: 'white',
            textAlign: 'center',
          }}
        >
          <Typography variant="h2" component="h1" gutterBottom>
            Welcome to MealMonitor
          </Typography>
          <Typography variant="h5" component="p" sx={{ mb: 4 }}>
            Community Driven Canteen Food Quality Platform
          </Typography>
          <Box sx={{ display: 'flex', gap: 2, justifyContent: 'center' }}>
            <Button
              variant="contained"
              size="large"
              onClick={() => navigate('/register')}
              sx={{ bgcolor: 'white', color: 'primary.main' }}
            >
              Get Started
            </Button>
            <Button
              variant="outlined"
              size="large"
              onClick={() => navigate('/login')}
              sx={{ borderColor: 'white', color: 'white' }}
            >
              Login
            </Button>
          </Box>
        </Paper>

        {/* Features Section */}
        <Typography variant="h3" component="h2" align="center" gutterBottom sx={{ mb: 6 }}>
          Features
        </Typography>
        <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', sm: '1fr 1fr', md: '1fr 1fr 1fr 1fr' }, gap: 4 }}>
          {features.map((feature, index) => (
            <Card key={index} sx={{ height: '100%', textAlign: 'center', p: 2 }}>
              <CardContent>
                <Box sx={{ mb: 2 }}>
                  {feature.icon}
                </Box>
                <Typography variant="h5" component="h3" gutterBottom>
                  {feature.title}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {feature.description}
                </Typography>
              </CardContent>
            </Card>
          ))}
        </Box>

        {/* About Section */}
        <Box sx={{ mt: 8, textAlign: 'center' }}>
          <Typography variant="h4" component="h2" gutterBottom>
            About MealMonitor
          </Typography>
          <Typography variant="body1" sx={{ maxWidth: 800, mx: 'auto' }}>
            MealMonitor is a comprehensive platform designed to enhance the canteen experience
            in educational institutions. Our platform allows students to review food items,
            get real-time notifications about menu changes, and provides administrators with
            tools to manage canteen operations effectively.
          </Typography>
        </Box>
      </Box>
    </Container>
  );
};

export default Home;
