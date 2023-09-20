# Design of streaming application.

### Technical requirements
#### Languages used: 
**Server-side:**
* Java (or GoLang)
* SpringBoot

**Client**:
* HTML
* JavaScript
* React
* React Native

**Libraries/SDKs**
We need to enable DASH/HLC therefore we are still deciding between several options that can be:

**Server-side:**
*used to multiplex, demultiplex, and segment media streams.*
* FFmpeg
* Shaka Packager
* Bento4

**Client-side:**
    **1. Web (still deciding):**
* Shaka Player
* Dash.js

**2. Mobile:**
  * react-native-video
