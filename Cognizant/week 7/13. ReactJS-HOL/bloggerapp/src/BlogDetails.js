import React from 'react';

function BlogDetails({ blogs }) {
  return (
    <div style={{ padding: '10px' }}>
      <h2>Blog Details</h2>
      {blogs.map(blog => (
        <div key={blog.id} style={{ marginBottom: '15px' }}>
          <h3>{blog.title}</h3>
          <p><b>{blog.author}</b></p>
          <p>{blog.description}</p>
        </div>
      ))}
    </div>
  );
}

export default BlogDetails;

