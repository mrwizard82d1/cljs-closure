# cursive-nrepl

Experimenting with Cursive for ClojureScript development.

This project is based on the page
https://lumberjaph.net/cursive-nrepl-cljs-oh-my/ retrieved on 23-Feb-2015.

## Usage

In general, I followed the steps outlined in the post. One *gotcha* that I discovered was the post indicated looking at the page `localhost:4000`. I believe the author used his virtual machine and Vagrant to map from port 3000 to port 4000. I do not perform that mapping; therefore, I can interact with the page by pointing my browser to `localhost:3000` before attempting to interact with the page.

A second issue I discovered early is that he issues two commands in the shell:

* `export AUSTIN_DEFAULT_SERVER_PORT=4343`
* `lein repl :host 0.0.0.0 :port 4242`

I originally interpreted the post as a single command (which failed).

In addition, since I am not running these commands in a virtual machine, I do not need the first command only the second. (See the GitHub page for cljs-start, https://github.com/magomimmo/cljs-start, for more information.

### Steps to create a bREPL.

Here are the steps to create a browser REPL (bREPL).

* Create a project using `lein new cljs-start <project-name>`.
* Wait patiently. This command may download many packages the first time you run it on a machine.
* In the project directory, execute `lein repl :headless :host 0.0.0.0 :port 4242`.
* More patient waiting until you see the "nREPL server started...." message.
* Open IntelliJ IDEA.
* Import the project you created (if you've not yet done so).
* Configure a **remote** REPL:
  * Execute *Run* > *Edit Configurations...*
  * Press *+* to add a new configuration.
  * Select *Clojure REPL* > *Remote*
  * Configure the host (either `localhost` or a specific host).
  * Configure the port: `4242`.
  * Give it a memorable name.
* Start the REPL you just created.
* Test it by entering `(+ 1 1 )` to see if it can add correctly.
  * Remember that `CTRL-ENTER` enters the command you are creating.
* Run the server by entering `(run)`.
* Verify it is running by;
  * Seeing the class name of the jetty server in the output window of IntelliJ.
  * Seeing the message in the shell window containing `SelectChannelConnector@0.0.0.0:3000`. (Note the port is **not** `4000`.)
* Run a ClojureScript REPL by executing `(browser-repl)`.
  * wait for the message `REPL switched to ClojureScript`.
* Open the link `localhost:3000`.
  * wait for the page with header `ClojureScript` and message `Welcome to ClojureScript!`.
* Enjoy!

To terminate the REPL process in the shell, press `CTRL-c` (twice in a cygwin indow).

### Additional considerations.

The `project.clj` file created by `cljs-start` defaults to advanced compilation mode which will produce many, many .js files from the Google Closure library.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
