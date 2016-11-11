(ns bossig.core)

(defn draw [state]
  (q/background  0 20 0)
  ;(println state)
 ; (println (get (:chords state) :note ))
  (q/with-translation [50 50 0]

                                        ;(drawinplace @midichords state)

;    (wavelines 1000 5 2 3 1 200 1200 14 10 1000 100 10 )
    )

;(wavelines 500 3 6 4 22 0 1200 0 10 500 280 2 )


;  (addpill (* (* 2 (mod4)) (get (:ld1 state) :note)) (* 2 (get (:ld1 state) :velocity)) 10  1)
  (updatepills) ;;;pillstate is a bit boring at the moment....
  (q/with-translation [50 50 (* 0 (get (:ch state) :pan ))]
    (renderpills state)
    )

  (thosesquares 5 50 (get (state :sd) :note) state )

;(drawinplace @midichords state)

(q/stroke 34 23 0)
(q/with-translation [-300 0 -1000])
(q/with-rotation [(* (mod8) (/ 3.14 4)) 0 0 1 ])

(dolines 0 0 0 2000 2000 1 10 100 (get  (state :sd) :note) (* (:tr state)  1000000000) 0 25 0 255 )

  (q/with-translation [0 -100 0]
;    (doboxes midibd state 100)
    )
  (q/with-translation [0 100 0]
 ;   (doboxes midild1 state 20)
    )

  (.sendScreen @server))
