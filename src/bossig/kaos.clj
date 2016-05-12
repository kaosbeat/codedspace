(ns bossig.core

  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil.helpers.seqs :refer [seq->stream range-incl cycle-between steps]]
            [quil.applet :as qa])
  (:import ( 'codeanticode.syphon.SyphonServer))
  (:import ('jsyphon.JSyphonServer))
; (:import ('com.jogamp.newt.NewtFactory.*))
 ;(:import ('javax.media.opengl GLCanvas GLEventListener GL GLAutoDrawable))
 ; (:import ('javax.media.opengl.glu GLU))

  )



(def width 1440)
(def height 980)
(def server (atom nil))
(defn setup []
  (q/frame-rate 30)
 (reset! server (codeanticode.syphon.SyphonServer. (quil.applet/current-applet) "test"))
  ;;(:sserver (new SyphonServer [(qa/currentapplet), "test" ]) )
 ; {:server ( codeanticode.syphon.SyphonServer. (quil.applet/current-applet) "test2" )}
  )

(on-event [:midi :note-on]
          (fn [e]
         ;(println e)
            ;(println  (:channel e) )
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
                  ;(println "bd in ta house")
                  (swap! bd assoc :note note :velocity vel)
                  ;(println (concat (print "stuff = ") (print note)))
                  (if (= channel 1)
                    (swap! sd assoc :note note :velocity vel)
                    (if (= channel 2)
                      (swap! ch  assoc :note note :velocity vel)
                      (if (= channel 3)
                        (swap! oh assoc :note note :velocity vel)
                        (if (= channel 4)
                          (swap! pc1 assoc :note note :velocity vel)
                          (if (= channel 5)
                            (swap! pc2 assoc :note note :velocity vel)
                            (if (= channel 6)
                              (swap! ld1 assoc :note note :velocity vel)
                              (if (= channel 7)
                                (swap! ld2 assoc :note note :velocity vel)
                                (if (= channel 8)
                                  (swap! chords assoc :note note :velocity vel)
                                  (if (= channel 9)
                                    (swap! keyz assoc :note note :velocity vel) )))))))))))))
          ::keyboard-handler)
;(remove-event-handler ::keyboard-handler)

(println @bd)
@bd

(on-event [:midi :control-change]
          (fn [e]
            ;(println e)
            (let [data1 (:data1 e)
                  data2 (:data2 e)
                  channel (:channel e)]
              (if (and  (= channel 1) (= data1 10))
                (swap! ch assoc :pan data2)
                )
              )
            )
          ::control-handler
          )

;(defn update [state            ])

(defn update [state]
    {
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
   :keyz @keyz

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
  (q/background 25 (* 2 (get @sd :velocity)) 255)
  )

(defn draw [state]


  (q/background 25 (* 2 (get @sd :velocity)) 255)


;;
  (q/with-translation [(* ( get @ch :pan) 10  )  (* (get  @bd :velocity ) 10) 1 ]
    (q/fill 230 (get @ch :note ) (* 16 (mod16)))
    (q/rect 15 (* ( mod16) 0) 300 300)
    ;(println (mod16))
    )


  (q/with-rotation [(get @chords :velocity) 1 1 0]

    (q/with-translation [ (+ 100 (* 50 (mod @bbeat 16))) 100 0]
      (q/fill  (get @sd :velocity ) (* 16 (mod16)) 12 )
      (q/box (* (get @bd :velocity)  10))
      ))
  (dotimes [n (get @sd :velocity)]
    (q/line (* 20 n) 400 (* n 100)  10)
    )

  (.sendScreen @server )
  ;(.sendScreen ( :server state))
  )




(q/defsketch halic
  :title "halic"
  ;;  :size :fullscreen
  :size [width height]
  :features [:present]
  :setup setup
  :update update
  :draw draw
  :renderer :p3d
  :middleware [m/fun-mode]
  )
