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

(def bd (atom {:note 0 :velocity 0}))
(def sd (atom {:note 0 :velocity 0}))
(def ch (atom 1))
(def oh (atom 1))
(def pc1 (atom 1))
(def pc2 (atom 1))
(def ld1 (atom 1))
(def ld2(atom 1))
(def ch (atom 1))
(def pi (atom 1))


@bbeat

(mod16)
