const products = Array.from({ length: 100 }, (_, index) => ({
  id: index + 1,
  name: `Product ${index + 1}`,
  price: Math.floor(Math.random() * 100) + 10,
  category: ['Electronics', 'Clothing', 'Books'][Math.floor(Math.random() * 3)],
}));

export default products;