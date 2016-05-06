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
; poc code, the beatcount @bbeat is increased on EVERY incoming midi message, this need to be filtered
(on-event [:midi :note-on]
          (fn [e]
            (let [note (:note e)
                  vel  (:velocity e)]
               (swap! bbeat inc)
               (println @bbeat)))
                    ::keyboard-handler)
