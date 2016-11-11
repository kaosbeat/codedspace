(ns bossig.core



                                        ; (:import ('com.jogamp.newt.NewtFactory.*))
                                        ;(:import ('javax.media.opengl GLCanvas GLEventListener GL GLAutoDrawable))
 ; (:import ('javax.media.opengl.glu GLU))



  (:use [overtone.live]
        [overtone.inst.piano]))





(piano)
(midi-connected-devices)
(event-debug-on)
(event-debug-off)


(def width 1440)
(def height 980)
;(def width 1920)
;(def height 1200)                                        ;beatclock
(def bbeat (atom 1))



(defn mod16 [] (mod @bbeat 16))
(defn mod8 [] (mod @bbeat 8))
(defn mod4 [] (mod @bbeat 4))
(defn mod2 [] (mod @bbeat 2))
; poc code, the beatcount @bbeat is increased on EVERY incoming midi message, this need to be filtered


(def bd (atom {:note 0 :velocity 0}))
(def sd (atom {:note 0 :velocity 0}))
(def ch (atom {:note 0 :velocity 0 :pan 0}))
(def oh (atom {:note 0 :velocity 0}))
(def pc1 (atom {:note 0 :velocity 0}))
(def pc2 (atom {:note 0 :velocity 0}))
(def ld1 (atom {:note 0 :velocity 0}))
(def ld2 (atom {:note 0 :velocity 0}))
(def chords (atom {:note 0 :velocity 0}))
(def keyz (atom {:note 0 :velocity 0}))


; adapted from http://blog.josephwilk.net/
; needs https://github.com/josephwilk/mud
(defsynth data-probes [timing-signal-bus 0]
  (let [beat-count (in:kr timing-signal-bus)
        _  (tap "global-beat-count" 60 (a2k beat-count))]
    (out 0 0)))

;(def active-data-probes (data-probes (:count time/beat-1th)))
