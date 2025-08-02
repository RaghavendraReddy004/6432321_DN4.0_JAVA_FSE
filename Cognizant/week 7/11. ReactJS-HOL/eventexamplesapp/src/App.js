import React, { useState } from 'react';
import CurrencyConverter from './components/CurrencyConverter';


function App() {
  const [count, setCount] = useState(0);

  const handleIncrement = () => {
    setCount(prev => prev + 1);
    sayHello();
  };

  const sayHello = () => {
    alert("Hello! Keep counting up!");
  };

  const handleDecrement = () => {
    setCount(prev => prev - 1);
  };

  const sayWelcome = (message) => {
    alert(`Say Welcome: ${message}`);
  };

  const handleSyntheticEvent = () => {
    alert('I was clicked');
  };

  return (
    <div style={{ padding: '20px', textAlign: 'left' }}>
      <h2>Counter Value: {count}</h2>
      <button onClick={handleIncrement}>Increment</button>
      <button onClick={handleDecrement}>Decrement</button>

      <br /><br />
      <button onClick={() => sayWelcome("welcome")}>Say Welcome</button>
      <br /><br />
      <button onClick={handleSyntheticEvent}>Click on me</button>

      <br /><br />
      <CurrencyConverter />
    </div>
  );
}

export default App;
