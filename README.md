# Android Chat.UCF - Location Based Chatrooms

Chat.UCF is a location-based messenger mobile application available for Android. Our application allows users to communicate in real-time, inside of chat rooms designated for specific areas on the UCF (University of Central Florida) campus. The location of users is tracked via GPS and available chat rooms are presented to the user upon login, in a sorted list based on how close the user is to each designated area. Users are able to view any chat room in the list but can only participate in the conversation if they are located on the UCF campus. We chose to limit the scop of our users to people only on campus to encourage interaction and connection amongst peers at UCF and limit potential negative activity that may arise.

<img src="img/Screen Shot 2016-07-02 at 3.05.34 PM.png" width="800">

## Modifications and Features
1. Established multiple chatrooms
2. Sort chat rooms by distance from user
2. Send/Receive messages
3. Facebook Login
4. Designated chatrooms across campus
5. Shows available users in chatroom
6. Geofencing–2 mile range
7. 200 character message limit


### Authentication
To enable authentication of our user base, users must sign into the application with Facebook in order to utilize their real name as their username in the chat rooms. We hope that this system encourages our users to be helpful and informative, while meeting new people in the process. 

## System States
Note: Both states MUST have WiFi or LTE and Location TURNED ON, in order for the application to work. 
####Using this system while on UCF campus:
While the user is on the UCF campus, they are able to join and leave any chat room. They are also able to send and receive messages while in these chat rooms.
####  Using this system while off UCF campus:
While the user is not on the UCF campus, they are still able to join and leave any chat room. The difference is they are still able to receive but they cannot send any messages within these chat rooms.



## Class Project and PubNub's Role
This app was built for COP 4331 - Processes of Object Oriented Programming at University of Central Florida in Spring 2016. We are publishing this open-source for anyone to use, but we are not liable for anything (such as cheating, plagiarism, or malfunctions) from users of this project. 

Currently, it only runs on Android. For GPS activity to work correctly, it must be used on an actual device, not an emulator.

We base our app from PubNub's framework, which we greatly modified for our purposes. PubNub's information may be found here:
[Download APK Here](http://kevingleason.me/pubnub-android-lolli-chat/)

### Make it your own!

We encourage you to expand on this application! You could incorporate new chat room features, modify interactions, add content, etc. Its up to you!

If you choose to do these, you will need your own Pub and Sub keys. To get these, [sign up for a PubNub account](http://www.pubnub.com/get-started/), it's quick and easy! You can find your unique PubNub keys in the [PubNub Developer Dashboard](https://admin.pubnub.com). Then, enable and customize the Presence, Storage & Playback, and Push Notification features. Once you have done this, simply replace the `String` values for `PUBLISH_KEY` and `SUBSCRIBE_KEY` in `me.kevingleason.pubnubchat.Constants`. 

To enable Push Notifications, you will need to [register your app for GCM](https://developers.google.com/cloud-messaging/android/client). You will then receive an API Key which you can use to enable Push Notifications in the [PubNub Developer Dashboard](https://admin.pubnub.com), as well as a Sender ID which you should use to replace the `GCM_SENDER_ID` in the `me.kevingleason.pubnubchat.Constants` file.
