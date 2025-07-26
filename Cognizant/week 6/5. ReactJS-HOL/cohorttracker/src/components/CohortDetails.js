// src/components/CohortDetails.js
import React from "react";
import styles from "./CohortDetails.module.css";

const CohortDetails = ({ cohort }) => {
  const { name, startedOn, status, coach, trainer } = cohort;

  const headingStyle = {
    color: status === "Ongoing" ? "green" : status === "Scheduled" ? "blue" : "black"
  };

  return (
    <div className={styles.box}>
      <h3 style={headingStyle}>{name}</h3>
      <p><strong>Started On:</strong> {startedOn}</p>
      <p><strong>Status:</strong> {status}</p>
      <p><strong>Coach:</strong> {coach}</p>
      <p><strong>Trainer:</strong> {trainer}</p>
    </div>
  );
};

export default CohortDetails;
