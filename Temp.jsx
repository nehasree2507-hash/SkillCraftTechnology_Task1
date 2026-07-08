import React, { useState } from 'react'

const Temp = () => {
  const [cel, setCel] = useState("");
  const [fah, setFah] = useState("");
  const [kel, setKel] = useState("");

  function handlecel(e) {
    const value = e.target.value;
    setCel(value);
    setFah(value ? (value * 1 * 9 / 5) + 32 : "");
    setKel(value ? (value * 1) + 273.15 : "");
  }

  function handlefah(e) {
    const value = e.target.value;
    setFah(value);
    setCel(value ? (value * 1 - 32) * 5 / 9 : "");
    setKel(value ? (value * 1 - 32) * 5 / 9 + 273.15 : "");
  }

  function handlekel(e) {
    const value = e.target.value;
    setKel(value);
    setCel(value ? (value * 1) - 273.15 : "");
    setFah(value ? (value * 1 - 273.15) * 9 / 5 + 32 : "");
  }

  return (
    <div className='main-container'>
      <div className='content'>
        <h1 className='heading'>Temperature Converter</h1>
        
        <div className='cards-grid'>
          <div className='temp-card celsius-card'>
            <div className='card-header'>
              <h2>Celsius</h2>
              <span className='symbol'>°C</span>
            </div>
            <input 
              type="number" 
              value={cel} 
              onChange={handlecel} 
              placeholder='Enter temperature'
              className='temp-input'
            />
          </div>

          <div className='temp-card fahrenheit-card'>
            <div className='card-header'>
              <h2>Fahrenheit</h2>
              <span className='symbol'>°F</span>
            </div>
            <input 
              type="number" 
              value={fah} 
              onChange={handlefah} 
              placeholder='Enter temperature'
              className='temp-input'
            />
          </div>

          <div className='temp-card kelvin-card'>
            <div className='card-header'>
              <h2>Kelvin</h2>
              <span className='symbol'>K</span>
            </div>
            <input 
              type="number" 
              value={kel} 
              onChange={handlekel} 
              placeholder='Enter temperature'
              className='temp-input'
            />
          </div>
        </div>
      </div>
    </div>
  )
}

export default Temp