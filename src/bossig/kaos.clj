(ns bossig.kaos
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil.helpers.seqs :refer [seq->stream range-incl cycle-between steps]])
  )

(def width 1440)
(def height 980)

(defn setup []
  (q/frame-rate 30))



(defn update [state]
  ;(newt/add-new-particles)
  ;(newt/update-particles width height)
  {
                                        ;:fm @(:left fmtonestaps)
   ;:beat @bbeat
   ;:drumbus @(audio-bus-monitor 0)
   ;:contrabus @(audio-bus-monitor 2)
   ;:fmtonesbus @(audio-bus-monitor 4)
   ;:fmchordsbus @(audio-bus-monitor 6)
   ;:axobus  @(get-in axotapper [:taps :left])
   ;:kickA (getkick)
   ;:snareA (get (get-in @live-pats [snareA]) (mod @bbeat (count (get-in @live-pats [snareA]))))
   ;:c-hat (getchat)
   ;:fmchord (get  (getchords) :carrier)
   ;:fmtones (nth (map #(or (:carrier %) (:depth %) 0)  (get-in @live-pats [fmtones])) (mod @bbeat (count (get-in @live-pats [fmtones]))))
   ;:contra (get (get-in @live-pats [contra])(mod @bbeat (count (get-in @live-pats [contra]))))
   })


(defn draw [state]
                                        ; (kn/drawP state)
  (q/background 25 25 255)
  (q/rect 25 250 300 300)

                                        ;(q/with-translation [ (+ 100 (* 50 (mod @bbeat 16))) 100 0]
                                        ; (q/box (* (:fm state)  100))

                                        ;  (q/box (* 550 @(audio-bus-monitor 0))))
  )



(q/defsketch halic
  :title "halic";;  :size :fullscreen
  :size [width height]
  :features [:present]
  :setup setup
  :update update
  :draw draw
  :renderer :p3d
  :middleware [m/fun-mode])
