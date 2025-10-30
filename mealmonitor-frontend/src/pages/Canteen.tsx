import React, { useState, useEffect } from 'react';
import {
  Container,
  Typography,
  Card,
  CardContent,
  CardActions,
  Button,
  Box,
  Chip,
  TextField,
  InputAdornment,
  Alert,
  CircularProgress,
} from '@mui/material';
import { Search, Add, Edit, Delete } from '@mui/icons-material';
import axios from 'axios';
import { API_ENDPOINTS } from '../config/api';

interface CanteenItem {
  id: string;
  name: string;
  description: string;
  price: number;
  category: string;
  available: boolean;
  imageUrl?: string;
}

const Canteen: React.FC = () => {
  const [items, setItems] = useState<CanteenItem[]>([]);
  const [filteredItems, setFilteredItems] = useState<CanteenItem[]>([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const categories = ['All', 'Breakfast', 'Lunch', 'Dinner', 'Snacks', 'Beverages'];

  useEffect(() => {
    fetchItems();
  }, []);

  useEffect(() => {
    filterItems();
  }, [items, searchTerm, selectedCategory]);

  const fetchItems = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get(API_ENDPOINTS.CANTEEN_ITEMS, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setItems(response.data);
    } catch (err: any) {
      setError('Failed to load canteen items');
    } finally {
      setLoading(false);
    }
  };

  const filterItems = () => {
    let filtered = items;

    if (searchTerm) {
      filtered = filtered.filter(item =>
        item.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        item.description.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }

    if (selectedCategory !== 'All') {
      filtered = filtered.filter(item => item.category === selectedCategory);
    }

    setFilteredItems(filtered);
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
          Canteen Menu
        </Typography>

        {error && (
          <Alert severity="error" sx={{ mb: 3 }}>
            {error}
          </Alert>
        )}

        {/* Search and Filter */}
        <Box sx={{ mb: 4 }}>
          <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', sm: '1fr 1fr' }, gap: 2, alignItems: 'center' }}>
            <Box>
              <TextField
                fullWidth
                placeholder="Search items..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">
                      <Search />
                    </InputAdornment>
                  ),
                }}
              />
            </Box>
            <Box>
              <Box display="flex" gap={1} flexWrap="wrap">
                {categories.map((category) => (
                  <Chip
                    key={category}
                    label={category}
                    onClick={() => setSelectedCategory(category)}
                    color={selectedCategory === category ? 'primary' : 'default'}
                    variant={selectedCategory === category ? 'filled' : 'outlined'}
                  />
                ))}
              </Box>
            </Box>
          </Box>
        </Box>

        {/* Items Grid */}
        <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', sm: '1fr 1fr', md: '1fr 1fr 1fr' }, gap: 3 }}>
          {filteredItems.length === 0 ? (
            <Box sx={{ gridColumn: '1 / -1' }}>
              <Typography color="text.secondary" align="center">
                No items found.
              </Typography>
            </Box>
          ) : (
            filteredItems.map((item) => (
              <Card key={item.id} sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                <CardContent sx={{ flexGrow: 1 }}>
                  <Box display="flex" justifyContent="space-between" alignItems="flex-start" mb={1}>
                    <Typography variant="h6" component="h3">
                      {item.name}
                    </Typography>
                    <Chip
                      label={item.available ? 'Available' : 'Unavailable'}
                      color={item.available ? 'success' : 'error'}
                      size="small"
                    />
                  </Box>
                  <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                    {item.description}
                  </Typography>
                  <Box display="flex" justifyContent="space-between" alignItems="center">
                    <Chip
                      label={item.category}
                      color="primary"
                      variant="outlined"
                      size="small"
                    />
                    <Typography variant="h6" color="primary">
                      ${item.price}
                    </Typography>
                  </Box>
                </CardContent>
                <CardActions>
                  <Button size="small" startIcon={<Add />}>
                    Add to Cart
                  </Button>
                  <Button size="small" startIcon={<Edit />}>
                    Edit
                  </Button>
                  <Button size="small" startIcon={<Delete />} color="error">
                    Delete
                  </Button>
                </CardActions>
              </Card>
            ))
          )}
        </Box>
      </Box>
    </Container>
  );
};

export default Canteen;
