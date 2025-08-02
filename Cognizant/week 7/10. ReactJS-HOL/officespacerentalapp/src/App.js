import React from 'react';
import taskImage from './task10.jpg'; 
import './App.css';

function App() {
  const ItemName = {
    Name: "DBS",
    Rent: 50000,
    Address: "Chennai"
  };

  const colorClass = ItemName.Rent <= 60000 ? 'textRed' : 'textGreen';
  const element = "Office Space";

  return (
    <div className="container">
      <h1>{element}, at Affordable Range</h1>
      <img src={taskImage} alt="Task 10" width="25%" height="25%" />
      <h2>Name: {ItemName.Name}</h2>
      <h3 className={colorClass}>Rent Rs. {ItemName.Rent}</h3>
      <h3>Address: {ItemName.Address}</h3>
    </div>
  );
}

export default App;
