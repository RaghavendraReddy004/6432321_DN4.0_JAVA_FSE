import React from 'react';
import CourseDetails from './CourseDetails';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import './App.css';

function App() {
  const courses = [
    { id: 1, name: 'Angular', date: '4/5/2021' },
    { id: 2, name: 'React', date: '6/3/2021' },
  ];

  const books = [
    { id: 1, bname: 'Master React', price: 670 },
    { id: 2, bname: 'Deep Dive into Angular 11', price: 800 },
    { id: 3, bname: 'Mongo Essentials', price: 450 },
  ];

  const blogs = [
    { id: 1, title: 'React Learning', author: 'Stephen Biz', description: 'Welcome to learning React!' },
    { id: 2, title: 'Installation', author: 'Schewzdnier', description: 'You can install React from npm.' },
  ];

  return (
    <div className="App">
      <div className="app-container">
        <div className="section">
          <CourseDetails courses={courses} />
        </div>
        <div className="vertical-line"></div>
        <div className="section">
          <BookDetails books={books} />
        </div>
        <div className="vertical-line"></div>
        <div className="section">
          <BlogDetails blogs={blogs} />
        </div>
      </div>
    </div>
  );
}

export default App;
