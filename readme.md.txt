1. Google map
* Setup:
- create project in google api
- On tap Library -> gg maps android api -> click enable
- On tap Credentials -> crate Credentials -> API key -> copy your api
- In your android studio:
  <string name="mai_api_key">your key</string>
- Add library: 
  File -> Project structure -> app (Modules) -> tap Dependencies -> + button
  search lib: play-services-maps -> ok -> ok
* use:
-  <pre><code><fragment
        android:id="@+id/myMap"
        android:name="com.google.android.gms.maps.MapFragment"
        android:layout_width="368dp"
        android:layout_height="495dp"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="8dp"
        android:layout_marginLeft="8dp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginRight="8dp"
        app:layout_constraintRight_toRightOf="parent" /></code></pre>
- add manifest.xml

2. Sending email
- give email (send to), subject, message to send

3. Push Notification
- Have a function in MainActivity
- Give infomation in this
 notify = new Notification.Builder
                    (getApplicationContext()).setContentTitle("Message").setContentText("body").
                    setContentTitle("Has new food").setSmallIcon(R.drawable.places_ic_search).build();

4. Float button sharing
- Add library: 
  File -> Project structure -> app (Modules) -> tap Dependencies -> + button
  search lib: design -> ok -> ok
- in xml add:
 <pre><code><android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >
        <android.support.design.widget.FloatingActionButton
            android:id="@+id/fabSharing"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|right"
            android:layout_margin="20dp"
            android:src="@drawable/share"
            app:fabSize="normal"
            app:backgroundTint="#176cea"
            />
    </android.support.design.widget.CoordinatorLayout></code></pre>
- add infomation to function in MainActivity with animation

	