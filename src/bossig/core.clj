(ns bossig.core
  (:use [overtone.live]
        [overtone.inst.piano])

  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil.helpers.seqs :refer [seq->stream range-incl cycle-between steps]])

  )

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(piano)
(midi-connected-devices)
(event-debug-off)




                                        ;beatclock
(def bbeat (atom 1))

(defn mod16 [] (mod @bbeat 16))
(defn mod8 [] (mod @bbeat 8))
(defn mod4 [] (mod @bbeat 4))
(defn mod2 [] (mod @bbeat 2))
; poc code, the beatcount @bbeat is increased on EVERY incoming midi message, this need to be filtered
(on-event [:midi :note-on]
          (fn [e]
            (let [note (:note e)
                  vel  (:velocity e)]
              (if (and (= note 60) (= vel 64))
                (swap! bbeat inc)
                (println "really  wrongMIDI")
                )))
                    ::keyboard-handler)
