## Boxel 2 ##

**Boxel 2** is the second edition to the Boxel series. It's a 2D, fast-paced platformer game built for multiple devices including:

- Android
- Desktop
- Browser
- iOS

### Utilizes the Libgdx gaming libraries ###

Rather than catering for different gaming platforms, Libgdx allows for rapid development for **all** popular systems. Check it out: [http://libgdx.badlogicgames.com/](http://libgdx.badlogicgames.com/)

### Installation Instructions ###

To develop your application via Eclipse, you need to install the following pieces of software.

- [Java Development Kit 7+ (JDK) (6 will not work!)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Eclipse](http://www.eclipse.org/downloads/), the "Eclipse IDE for Java Developers" is usually sufficient.
- [Android SDK](http://developer.android.com/sdk/installing.html), you only need the SDK, not the ADT bundle, which includes Eclipse. Install all platforms via the SDK Manager!

Be sure to install these plugins. To do so, select the 'Help' option on the top bar, select 'Install New Software...", paste the links provided below into the "Work with:" field, and then click the "Add..." button.

- [Android Development Tools for Eclipse](http://developer.android.com/tools/sdk/eclipse-adt.html), aka ADT Plugin. Use this update site: [https://dl-ssl.google.com/android/eclipse/](https://dl-ssl.google.com/android/eclipse/)
- [Eclipse Integration Gradle](https://github.com/spring-projects/eclipse-integration-gradle/), use this update site: [http://dist.springsource.com/release/TOOLS/gradle](http://dist.springsource.com/release/TOOLS/gradle)

Once all of these tools are installed, proceed to [creating your project](https://github.com/libgdx/libgdx/wiki/Project-Setup-Gradle)

### Importing Boxel 2 into Eclipse ###

This part can get a little tricky. In order for you to properly set up the environment, please be sure to flow these steps:

- **Step 1**: Copy + Paste (or right click + export) Boxel2 into your workspace
- **Step 2**: In Eclipse select File > Import > Gradle > Gradle Project
- **Step 3**: Browse for your 'root' directory (ex: "C:\Users\doppler\EclipseWorkspace\Boxel2"), then click 'Build Model' (**See below if an error pops up**)
- **Step 4**: Select 'Select All' and then click 'Finish'

If a window pops up saying something like "The SDK directory 'C:\...' does not exist. See error log for details", you'll need to change your directory by following these steps:

- **Step 1**: Open your 'Boxel2' folder within your workspace.
- **Step 2**: Find a file labeled "local.properties" and open it with notepad or other text editors.
- **Step 3**: Change the directory to match where your 'android-sdk' folder is.
- **Step 4**: Save and restart the importing process as described above.

### Running the Program ###

- **Desktop**: Right click the desktop project, `Run As -> Java Application`. Select the desktop starter class (e.g. DesktopLauncher.java).
- **Android**: make sure you have a device connected and that it shows up in DDMS ([see Android Developer Guide](http://developer.android.com/guide/index.html)). Right click your Android project, `Run As -> Android Application`.
- **iOS RoboVM**: Right click the robovm project, `Run As -> iOS Device App` to run on a connected device, or `Run As -> iOS Simulator App` to run on the iOS simulator. If you run on a device, you need to [provision](https://developer.apple.com/library/ios/documentation/IDEs/Conceptual/AppDistributionGuide/Introduction/Introduction.html) it to be able to deploy to it!
- **HTML5**: Right click the html project, `Run As -> External Tools Configuration`. Create a new configuration by double clicking the `Program` entry in the left sidebar. Give the configuration a name, e.g. GWT SuperDev. Set the location field to the `gradlew.bat` (Windows) or `gradlew` (Linux, Mac) file. Set the working directory to the root folder of your project. Specify `html:superDev` as the Argument. Press 'Apply', then 'Run'. Wait until you see the message `The code server is ready.` in the console view, then open the URL [http://localhost:8080/html](http://localhost:8080/html). You can leave the server running. If you change code or assets, simply click the `SuperDev Refresh `button in the browser. This will recompile your app and reload the site.