import React from 'react';
import { View, StyleSheet } from 'react-native';
import Video from 'react-native-video';

const App = () => {
  return (
    <View style={styles.container}>
      <Video
        source={{ uri: 'https://samplelib.com/lib/preview/mp4/sample-5s.mp4' }} // Can be a URL or a local file
        resizeMode="cover" // Fill the whole screen at aspect ratio.
        style={styles.video}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#333',
  },
  video: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
  },
});

export default App;
