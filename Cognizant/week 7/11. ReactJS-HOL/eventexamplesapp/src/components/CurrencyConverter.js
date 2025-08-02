import React, { useState } from 'react';

function CurrencyConverter() {
  const [amount, setAmount] = useState('');

  const handleSubmit = () => {
    const rupees = parseFloat(amount);
    if (isNaN(rupees)) {
      alert("Please enter a valid amount");
      return;
    }
    const euro = rupees * 0.011; 
    alert(`Converted Amount: €${euro.toFixed(2)}`);
  };

  return (
    <div>
      <h2 style={{ color: 'green' }}>Currency Converter!!!</h2>
      <label>Amount</label><br />
      <input
        type="text"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
        placeholder="Enter amount in INR"
      />
      <br /><br />
      <label>Currency</label><br />
      <input type="text" value="Euro" disabled />
      <br /><br />
      <button onClick={handleSubmit}>Convert</button>
    </div>
  );
}

export default CurrencyConverter;
