;;; ****************************** NOTES ******************************
;;; Defines four profiles:
;;;
;;; - :shared
;;; - :dev
;;; - :simple
;;; - :advanced
;;;
;;; the :dev, :simple and :advanced profiles are composite profiles,
;;; meaning that they share the content of :shared profile.
;;; *******************************************************************

{:shared {:clean-targets ["out" :target-path]
          :test-paths ["test/clj" "test/cljs"]
          :resources-paths ["resources"]
          :plugins [[com.cemerick/clojurescript.test "0.3.1"]]
          :cljsbuild
          {:builds {:cljs-closure
                    {:source-paths ["test/cljs"]
                     :compiler
                     {:output-dir "resources/public/js"
                      :source-map "resources/public/js/cljs_closure.js.map"}}}
           :test-commands {"phantomjs"
                           ["phantomjs" :runner "resources/public/js/cljs_closure.js"]}}}
 :dev [:shared
       {:source-paths ["resources/tools/http" "resources/tools/repl"]
        :dependencies [[ring "1.3.1"]
                       [compojure "1.2.1"]
                       [enlive "1.1.5"]]
        :plugins [[com.cemerick/austin "0.1.5"]]
        :cljsbuild
        {:builds {:cljs-closure
                  {:source-paths ["resources/tools/repl"]
                   :compiler
                   {:optimizations :whitespace
                    :pretty-print true}}}}
        
        :injections [(require '[ring.server :as http :refer [run]]
                              'cemerick.austin.repls)
                     (defn browser-repl []
                       (cemerick.austin.repls/cljs-repl (reset! cemerick.austin.repls/browser-repl-env
                                                                (cemerick.austin/repl-env))))]}]
 ;; simple profile.
 :simple [:shared
          {:cljsbuild
           {:builds {:cljs-closure
                     {:compiler {:optimizations :simple
                                 :pretty-print false}}}}}]
 ;; advanced profile
 :advanced [:shared
            {:cljsbuild
             {:builds {:cljs-closure
                       {:source-paths ["test/cljs"]
                        :compiler
                        {:optimizations :advanced
                         :pretty-print false}}}}}]}

