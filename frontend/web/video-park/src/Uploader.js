import React, { useState } from 'react';

function Uploader() {
  const [file, setFile] = useState(null);

  const chooseFile = (event) => {
    setFile(event.target.files[0]);
  };

  const uploadFile = () => {
    if (file) {
      const formData = new FormData();
      formData.append('file', file);

        fetch('http://54.151.27.225:8080/upload', {
          method: 'POST',
          body: formData,
        })
        .then((response) => response.json())
        .then((result) => {
          console.log('Success:', result);
        })
        .catch((error) => {
          console.error('Error:', error);
        });
    }
  };

  return (
    <div>
      <input type="file" onChange={chooseFile} />
      <button onClick={uploadFile}>Upload</button>
    </div>
  );
}

export default Uploader;
