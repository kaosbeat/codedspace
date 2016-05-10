(ns bossig.core

  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil.helpers.seqs :refer [seq->stream range-incl cycle-between steps]])
  (:import [codeanticode.syphon])
  )

(def width 1440)
(def height 980)

(defn setup []
  (q/frame-rate 30)

  )

(on-event [:midi :note-on]
          (fn [e]
           ; (println e)
            ;(print (:channel e) )
            ;(print " ")
            ;(print (:note e) )
            ;(print " ")


            (let [note (:note e)
                  vel  (:velocity e)
                  channel (:channel e)]
              (if (and (= channel 15) (and (= note 60) (= vel 64)))
                (swap! bbeat inc)
                ;(concat (print "note =") (println  note ))
                (if (= channel 0)
                  (swap! bd assoc :note note :velocity vel)
                  ;(println (concat (print "stuff = ") (print note)))
                  (if (= channel 1)
                    (swap! sd assoc :note note :velocity vel)
                    (if (= channel 2)

                      ))))

              ))
          ::keyboard-handler)

(on-event [:midi :control-change]
          (fn [e]
            ;(println e)
            (let [data1 (:data1 e)
                  data2 (:data2 e)
                  channel (:channel e)]
              (if (and  (= channel 2) (= data1 1))
                (swap! ch assoc :pan data2)
                )
              )
            )
          ::control-handler
          )



(defn update [state]
  ;(newt/add-new-particles)
  ;(newt/update-particles width height)
  {
                                        ;:fm @(:left fmtonestaps)
   :beat @bbeat
   :bd @bd
   :sd @sd
   :ch @ch
   :oh @oh
   :pc1 @pc1
   :pc2 @pc2
   :ld1 @ld1
   :ld2 @ld2
   :chords @chords
   :keys @keys

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
                                        ;(q/text  0 0 10 10 )

  (q/background 25 25 255)
  (q/fill 0 (get @sd :velocity ) 2)
  (q/with-translation [(* ( get @ch :pan) 10  )  (* (get  @bd :velocity ) 10) 1 ]
    (q/rect 15 (* ( mod16) 0) 300 300)
    (println (mod16))
    )

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
