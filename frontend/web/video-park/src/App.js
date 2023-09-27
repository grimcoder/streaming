import './App.css';
import React from "react";
import { useEffect, useState } from "react";

import ShakaPlayer from 'shaka-player-react';
import 'shaka-player/dist/controls.css';
import Uploader from './Uploader';
import List from './List';

function App() {
  const [clip, setClip]= useState('');
  function selected(clip){
    setClip(clip)
  }

  return (
    <div className="App">
      <ShakaPlayer src={clip} />
      <Uploader />
      <List onSelect={selected} />
    </div>
  );
}

export default App;