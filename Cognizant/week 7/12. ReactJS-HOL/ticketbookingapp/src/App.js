import React, { useState } from 'react';
import './App.css';  

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
      {isLoggedIn ? (
        <>
          <h2>Welcome back, now book tickets</h2>
          <button onClick={handleLogout}>Logout</button>
        </>
      ) : (
        <>
          <h2>Please sign up to book tickets</h2>
          <button onClick={handleLogin}>Login</button>
        </>
      )}
    </div>
  );
}

export default App;
