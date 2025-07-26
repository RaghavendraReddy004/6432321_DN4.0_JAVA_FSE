import React, { Component } from "react";

class Cart extends Component {
  render() {
    return (
      <tr className="cart-row">
        <td>{this.props.itemname}</td>
        <td>{this.props.price}</td>
      </tr>
    );
  }
}

export default Cart;
