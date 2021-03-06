(defproject cljs-closure "0.1.0-SNAPSHOT"
  :description "Experimenting with Google Closure library."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}

  :min-lein-version "2.3.4"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 ;; Even though the latest version of ClojureScript is
                 ;; 2913, this version causes warnings about multiple
                 ;; declarations of the google.closure namespace. Even
                 ;; though these are warnings, they seem to cause some
                 ;; instability with the interpreter. version 2511
                 ;; seems to be the last "clean" interpreter.
                 [org.clojure/clojurescript "0.0-2511"]]

  ;; We need to add src/cljs too, because cljsbuild does not add its
  ;; source-paths to the project source-paths
  :source-paths ["src/clj" "src/cljs" "test/cljs"]

  :plugins [[lein-cljsbuild "1.0.5"]]

  :hooks [leiningen.cljsbuild]

  :cljsbuild
  {:builds {;; This build is only used for including any cljs source
            ;; in the packaged jar when you issue lein jar command and
            ;; any other command that depends on it
            :cljs-closure
            {:source-paths ["src/cljs"]
             ;; The :jar true option is not needed to include the CLJS
             ;; sources in the packaged jar. This is because we added
             ;; the CLJS source codebase to the Leiningen
             ;; :source-paths
             ;:jar true
             ;; Compilation Options
             :compiler
             {:output-to "resources/public/js/cljs_closure.js"
              :optimizations :whitespace
              :pretty-print true}}}})
