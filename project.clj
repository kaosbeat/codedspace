(defproject bossig "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :resource-paths [
                   "Syphon/library/jsyphon.jar"
                   "Syphon/library/libJSyphon.jnilib"
                   "Syphon/library/Syphon.framework"
                   "Syphon/library/Syphon.jar"
                   ]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [overtone "0.10.1"]
                 [leipzig "0.10.0"]
                 [quil "2.4.0"]
                 ]
  :main bossig.core)
