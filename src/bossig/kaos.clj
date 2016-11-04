(ns bossig.core

  (:require [quil.core :as q]
            [quil.middleware :as m]
            [quil.helpers.seqs :refer [seq->stream range-incl cycle-between steps]]
            [quil.applet :as qa]
            [bossig.kaosinst])
  (:import ( 'codeanticode.syphon.SyphonServer))
  (:import ('jsyphon.JSyphonServer))
                                        ; (:import ('com.jogamp.newt.NewtFactory.*))
                                        ;(:import ('javax.media.opengl GLCanvas GLEventListener GL GLAutoDrawable))
 ; (:import ('javax.media.opengl.glu GLU))

  )



(def width 1440)
(def height 980)
(def width 1920)
(def height 1200)
(def server (atom nil))
(def tr (seq->stream (cycle-between 1 1 16 0.1 0.1)))
(defsynth tapper
  []
  (let [source (sound-in 2 1)
        left (select 0 source)
        right (select 1 source)
        _ (tap :left 10 left)
        _ (tap :right 10 right)
        _ (tap :phase 10 (- left right))]
    (out [0 1] [left right])))

(def intap (tapper))


(defn setup []
  (q/frame-rate 30)
 (reset! server (codeanticode.syphon.SyphonServer. (quil.applet/current-applet) "test"))
  ;;(:sserver (new SyphonServer [(qa/currentapplet), "test" ]) )
 ; {:server ( codeanticode.syphon.SyphonServer. (quil.applet/current-applet) "test2" )}
  )


;(remove-event-handler ::keyboard-handler)

;;(println @bd)








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

   :tr (tr)

   :drumbus @(audio-bus-monitor 0)
   ;:contrabus @(audio-bus-monitor 2)
   ;:fmtonesbus @(audio-bus-monitor 4)
   ;:fmchordsbus @(audio-bus-monitor 6)
   :bus  @(get-in intap [:taps :left])
   ;:kickA (getkick)

   ;:snareA (get (get-in @live-pats [snareA]) (mod @bbeat (count (get-in @live-pats [snareA]))))
   ;:c-hat (getchat)
   ;:fmchord (get  (getchords) :carrier)
   ;:fmtones (nth (map #(or (:carrier %) (:depth %) 0)  (get-in @live-pats [fmtones])) (mod @bbeat (count (get-in @live-pats [fmtones]))))
   ;:contra (get (get-in @live-pats [contra])(mod @bbeat (count (get-in @live-pats [contra]))))
    :lines (swap! linesdyn assoc 6 (tr))
     }

)

(defn draw [state]
  (q/background  100 120 0)
                                        ;(println (:lines state))

  (addpill (* 50 (get (:ld1 state) :note)) (* -20 (get (:ld1 state) :velocity)) 0  200)
  (q/with-translation [500 500 0]
    ;(circlejoy state)
    )



  (dotimes [n (count @kick)]
    (q/with-translation [(* n (/ width (count @kick))) (* (get @kick n) (* (tr) 20)) -1000]
                                        ; (q/box (get (:bd state) :note) )
      (q/with-rotation [(*  (get @kick n) (get (:sd state) :velocity)) 1 0 1 ]
        (if (= n (mod8))
          (bassdrum state)

          ))
                                        ;      (clicktrack state)
      ))
  (q/with-translation [ 500 0 -1000 ]
    (q/stroke-weight 1)
    ;(dynlines state)
    )
  (updatepills) ;;;pillstate is a bit boring at the moment....
  (q/with-translation [500 500 (* -10 (get (:ch state) :pan ))]
    (renderpills state)
    )



  (.sendScreen @server))


(defn draw [state]
  (q/background 0 (* 2 (get @sd :velocity)) (* (tr) 2))
  (q/with-translation [0 (get @sd :velocity) -300]
    (q/with-rotation [(* (/ 360 16) (mod16))  1 0 0 ]
      (dotimes [ x (get @bd :velocity)]
        (dotimes [ y (get @bd :velocity)]
          (q/with-rotation [(* 80  (get @sd :note)) 0 1 1])
          (q/with-translation [ (* x (get @bd :note) 10)
                                (* y -100)
                                (* 100 0) ]
            (q/fill (* (get @bd :velocity) 2 ) 0 23)
            (if (= x 2)
              (q/box 50)
              (q/box (* y 5))))
          ))))



  (.sendScreen @server)

  )

(defn draw [state]
  (q/background 0)


  (joywaves 600 10 [100 200])
(wavelines 1000 5 2 3 1 200 1200 14 10 1000 100 10 )
  (q/with-rotation [(* 10 (get @sd :velocity)) 0 1 0]



                                        ;( q/with-rotation [(get @bd :note) 0 1 0])


    (wavelines 100 10 2 3 1 200 1200 14 10 1000 10 10 )
    (q/stroke 255 34 43)
    (wavelines 14 300 2 3 1 200 120 14 10 100 300 30 ))




  (.sendScreen @server))






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
    (q/line (* 20 n) 400 (* n (get @bd :velocity ))  10)
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
