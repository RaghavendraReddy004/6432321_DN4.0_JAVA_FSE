import React from 'react';

const IndianPlayers = () => {
  const team = ["Virat", "Rohit", "Dhoni", "Jadeja", "Ashwin", "Gill"];

  // Destructuring using map index
  const oddPositions = ["First", "Third", "Fifth"];
  const evenPositions = ["Second", "Fourth", "Sixth"];

  const oddTeam = team.filter((_, index) => index % 2 === 0);
  const evenTeam = team.filter((_, index) => index % 2 !== 0);

  const T20Players = ["Surya", "Ishan", "Chahal"];
  const RanjiPlayers = ["Pujara", "Rahane", "Umesh"];

  const mergedPlayers = [...T20Players, ...RanjiPlayers];

  return (
    <div style={{ textAlign: "left", padding: "20px" }}>
      <h2>Odd Team Players:</h2>
      <ol>
        {oddTeam.map((player, index) => (
          <li key={index}>{oddPositions[index]}: {player}</li>
        ))}
      </ol>

      <h2>Even Team Players:</h2>
      <ol>
        {evenTeam.map((player, index) => (
          <li key={index}>{evenPositions[index]}: {player}</li>
        ))}
      </ol>

      <h2>Merged Indian Players:</h2>
      <ol>
        {mergedPlayers.map((player, index) => (
          <li key={index}>Mr. {player}</li>
        ))}
      </ol>
    </div>
  );
};

export default IndianPlayers;
