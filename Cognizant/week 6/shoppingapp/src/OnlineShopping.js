import React, { Component } from "react";
import Cart from "./Cart";
import "./styles.css";

class OnlineShopping extends Component {
  render() {
    const items = [
      { itemname: "Laptop", price: "₹90000" },
      { itemname: "Mobile", price: "₹55000" },
      { itemname: "Headphones", price: "₹5000" },
      { itemname: "Keyboard", price: "₹1500" },
      { itemname: "Monitor", price: "₹20000" },
    ];

    return (
      <div className="shopping-container">
        <h2 className="title">
          🛒 <span>Shopping Cart</span>
        </h2>
        <table className="cart-table">
          <thead>
            <tr>
              <th>ITEMS</th>
              <th>PRICE</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item, idx) => (
              <Cart key={idx} itemname={item.itemname} price={item.price} />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default OnlineShopping;
