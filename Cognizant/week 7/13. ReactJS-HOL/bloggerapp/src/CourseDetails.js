import React from 'react';

function CourseDetails({ courses }) {
  return (
    <div style={{ padding: '10px' }}>
      <h2>Course Details</h2>
      {courses.map(course => (
        <div key={course.id} style={{ marginBottom: '10px' }}>
          <h3>{course.name}</h3>
          <p>{course.date}</p>
        </div>
      ))}
    </div>
  );
}

export default CourseDetails;
