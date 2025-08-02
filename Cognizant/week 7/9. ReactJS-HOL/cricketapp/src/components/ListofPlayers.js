import React from 'react';

const ListofPlayers = () => {
  const players = [
    { name: "Virat", score: 85 },
    { name: "Rohit", score: 95 },
    { name: "Dhoni", score: 60 },
    { name: "Jadeja", score: 45 },
    { name: "Ashwin", score: 75 },
    { name: "Gill", score: 50 },
    { name: "Hardik", score: 90 },
    { name: "Bumrah", score: 65 },
    { name: "Pant", score: 30 },
    { name: "Surya", score: 78 },
    { name: "KL Rahul", score: 40 }
  ];

  const below70 = players.filter(player => player.score < 70);

  return (
    <div style={{ textAlign: "left", padding: "20px" }}>
      <h2>List of All Players:</h2>
      <ol>
        {players.map((player, index) => (
          <li key={index}>Mr. {player.name} - {player.score}</li>
        ))}
      </ol>

      <h3>Players with score less than 70:</h3>
      <ol>
        {below70.map((player, index) => (
          <li key={index}>Mr. {player.name} - {player.score}</li>
        ))}
      </ol>
    </div>
  );
};

export default ListofPlayers;
