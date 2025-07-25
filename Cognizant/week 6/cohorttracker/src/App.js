import './App.css';
import { CohortsData } from './Cohort';
import CohortDetails from './components/CohortDetails';

function App() {
  return (
    <div className="app-container">
      <h1 className="heading">Cohort Details</h1>
      <div className="cohort-grid">
        {CohortsData.map((cohort, index) => (
          <CohortDetails key={index} cohort={cohort} />
        ))}
      </div>
    </div>
  );
}

export default App;
