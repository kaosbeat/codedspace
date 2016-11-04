(defproject bossig "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repl-options {:host "0.0.0.0" :port 21337}
	:resource-paths [
                   "resources/Syphon/library/jsyphon.jar"
                   "resources/Syphon/library/libJSyphon.jnilib"
                   "resources/Syphon/library/Syphon.framework"
                   "resources/Syphon/library/Syphon.jar"
                   ]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [overtone "0.10.1"]
                 [leipzig "0.10.0"]
                 [quil "2.4.0"]
                 [glgraphics "1.0.0"]
                 ;;[com._0xab/jogl "2.1.3"] ;;needed for the syphon hack
                 ]


  :main bossig.core)
