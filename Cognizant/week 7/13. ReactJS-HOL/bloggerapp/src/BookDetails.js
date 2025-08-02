import React from 'react';

function BookDetails({ books }) {
  return (
    <div style={{ padding: '10px' }}>
      <h2>Book Details</h2>
      {books.map(book => (
        <div key={book.id} style={{ marginBottom: '10px' }}>
          <h4>{book.bname}</h4>
          <p>{book.price}</p>
        </div>
      ))}
    </div>
  );
}

export default BookDetails;
