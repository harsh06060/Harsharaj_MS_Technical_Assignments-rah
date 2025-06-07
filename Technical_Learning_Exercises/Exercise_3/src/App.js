// import logo from './logo.svg';
// import './App.css';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

// export default App;



// Inefficient Code
// import React, { useState } from 'react';
// import './App.css';
// import productsData from './data';

// // SearchBar Component
// function SearchBar(props) {
//   return (
//     <input
//       type="text"
//       placeholder="Search products..."
//       value={props.searchQuery}
//       onChange={(e) => props.onSearch(e.target.value)}
//     />
//   );
// }

// // SortControls Component
// function SortControls(props) {
//   return (
//     <select onChange={(e) => props.onSort(e.target.value)}>
//       <option value="name">Sort by Name</option>
//       <option value="price">Sort by Price</option>
//     </select>
//   );
// }

// // ProductCard Component
// function ProductCard({ product }) {
//   // Expensive calculation (simulated)
//   const calculateDiscount = (price) => {
//     let result = price;
//     for (let i = 0; i < 1000000; i++) {
//       result = price * 0.9; // Simulate heavy computation
//     }
//     return result.toFixed(2);
//   };

//   console.log(`Rendering ProductCard for ${product.name}`);
//   return (
//     <div className="product-card">
//       <h3>{product.name}</h3>
//       <p>Price: ${product.price}</p>
//       <p>Discounted: ${calculateDiscount(product.price)}</p>
//       <p>Category: {product.category}</p>
//     </div>
//   );
// }

// // ProductList Component
// function ProductList({ products }) {
//   return (
//     <div className="product-list">
//       {products.map((product) => (
//         <ProductCard key={product.id} product={product} />
//       ))}
//     </div>
//   );
// }

// // Dashboard Component
// function Dashboard() {
//   const [searchQuery, setSearchQuery] = useState('');
//   const [sortBy, setSortBy] = useState('name');

//   // Inefficient filtering (runs on every render)
//   const filteredProducts = productsData.filter((product) =>
//     product.name.toLowerCase().includes(searchQuery.toLowerCase())
//   );

//   // Inefficient sorting (runs on every render, creates new array)
//   const sortedProducts = [...filteredProducts].sort((a, b) => {
//     if (sortBy === 'name') {
//       return a.name.localeCompare(b.name);
//     } else {
//       return a.price - b.price;
//     }
//   });

//   // New functions created on every render
//   const handleSearch = (query) => {
//     setSearchQuery(query);
//   };

//   const handleSort = (sortType) => {
//     setSortBy(sortType);
//   };

//   return (
//     <div className="dashboard">
//       <h1>Product Dashboard</h1>
//       <SearchBar searchQuery={searchQuery} onSearch={handleSearch} />
//       <SortControls onSort={handleSort} />
//       <ProductList products={sortedProducts} />
//     </div>
//   );
// }

// function App() {
//   return (
//     <div className="App">
//       <Dashboard />
//     </div>
//   );
// }

// export default App;





// Efficient Code
import React, { useState, useMemo, useCallback } from 'react';
import './App.css';
import productsData from './data';

// SearchBar Component
function SearchBar({ searchQuery, onSearch }) {
  console.log('Rendering SearchBar');
  return (
    <input
      type="text"
      placeholder="Search products..."
      value={searchQuery}
      onChange={(e) => onSearch(e.target.value)}
    />
  );
}

// SortControls Component
function SortControls({ onSort }) {
  console.log('Rendering SortControls');
  return (
    <select onChange={(e) => onSort(e.target.value)}>
      <option value="name">Sort by Name</option>
      <option value="price">Sort by Price</option>
    </select>
  );
}

// ProductCard Component
function ProductCard({ product }) {
  // Memoize discount calculation
  const discountedPrice = useMemo(() => {
    let result = product.price;
    for (let i = 0; i < 1000000; i++) {
      result = product.price * 0.9; // Simulate heavy computation
    }
    return result.toFixed(2);
  }, [product.price]);

  console.log(`Rendering ProductCard for ${product.name}`);
  return (
    <div className="product-card">
      <h3>{product.name}</h3>
      <p>Price: ${product.price}</p>
      <p>Discounted: ${discountedPrice}</p>
      <p>Category: {product.category}</p>
    </div>
  );
}

// ProductList Component
function ProductList({ products }) {
  console.log('Rendering ProductList');
  return (
    <div className="product-list">
      {products.map((product) => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
  );
}

// Dashboard Component
function Dashboard() {
  const [searchQuery, setSearchQuery] = useState('');
  const [sortBy, setSortBy] = useState('name');

  // Memoize filtered products
  const filteredProducts = useMemo(() => {
    return productsData.filter((product) =>
      product.name.toLowerCase().includes(searchQuery.toLowerCase())
    );
  }, [searchQuery]);

  // Memoize sorted products
  const sortedProducts = useMemo(() => {
    return [...filteredProducts].sort((a, b) => {
      if (sortBy === 'name') {
        return a.name.localeCompare(b.name);
      } else {
        return a.price - b.price;
      }
    });
  }, [filteredProducts, sortBy]);

  // Memoize event handlers
  const handleSearch = useCallback((query) => {
    setSearchQuery(query);
  }, []);

  const handleSort = useCallback((sortType) => {
    setSortBy(sortType);
  }, []);

  return (
    <div className="dashboard">
      <h1>Product Dashboard</h1>
      <SearchBar searchQuery={searchQuery} onSearch={handleSearch} />
      <SortControls onSort={handleSort} />
      <ProductList products={sortedProducts} />
    </div>
  );
}

function App() {
  return (
    <div className="App">
      <Dashboard />
    </div>
  );
}

export default App;