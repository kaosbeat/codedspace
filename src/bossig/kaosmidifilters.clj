(ns bossig.core)


(on-event [:midi :timing-clock]
          (fn [e]
            (println e)
            ;(println  (:channel e) )
            ;(print " ")
            ;(print (:note e) )
            ;(print " ")


            )
          ::clock-handler
          )


(on-event [:midi :midi-time-code]
          (fn [e]
           ; (println e)
            ;(println  (:channel e) )
            ;(print " ")
            ;(print (:note e) )
            ;(print " ")


            )
          ::clock-handler2
          )

(remove-event-handler ::clock-handler)


;;midimaps
(def midimap0 (atom []))(def midimap1 (atom []))(def midimap2 (atom []))(def midimap3 (atom []))(def midimap4 (atom []))(def midimap5 (atom []))(def midimap6 (atom []))(def midimap7 (atom []))(def midimap8 (atom []))(def midimap9 (atom []))
(def midimap0map (atom []))(def midimap1map (atom []))(def midimap2map (atom []))(def midimap3map (atom []))(def midimap4map (atom []))(def midimap5map (atom []))(def midimap6map (atom []))(def midimap7map (atom []))(def midimap8map (atom []))(def midimap9map (atom []))
(def midibd (atom []))(def midisd (atom []))(def midioh (atom []))(def midich (atom []))(def midipc1 (atom []))(def midipc2 (atom []))(def midild1 (atom []))(def midild2 (atom []))(def midichords (atom []))(def midikeyz (atom []))

;; (on-event [:midi :note-on]
;;           (fn [e]
;;             ;;(println e)
;;             ;;(println "channel  ")
;;             ;;(print  (:channel e) )
;;             ;;(print "note ")
;;             ;;(print (:note e) )
;;             ;;
;;             ;;(print " ")


;;             (let [note (:note e)
;;                   vel  (:velocity e)
;;                   channel (:channel e)]
;;               ;;(println channel note vel)
;;               (if (and (= channel 15) (= note 60))
;;                 (swap! bbeat inc)
;;                                         ;(concat (print "note =") (println  note ))
;;                 (if (= channel 0)
;;                                         ;(println "bd in ta house")
;;                   (swap! bd assoc :note note :velocity vel)
;;                                         ;(println (concat (print "stuff = ") (print note)))
;;                   (if (= channel 1)
;;                     (swap! sd assoc :note note :velocity vel)
;;                     (if (= channel 2)
;;                       (swap! ch  assoc :note note :velocity vel)
;;                       (if (= channel 3)
;;                         (swap! oh assoc :note note :velocity vel)
;;                         (if (= channel 4)
;;                           (swap! pc1 assoc :note note :velocity vel)
;;                           (if (= channel 5)
;;                             (swap! pc2 assoc :note note :velocity vel)
;;                             (if (= channel 6)
;;                               (swap! ld1 assoc :note note :velocity vel)
;;                               (if (= channel 7)
;;                                 (swap! ld2 assoc :note note :velocity vel)
;;                                 (if (= channel 8)
;;                                   (midiparse midimap8 @midimap8 midimap8map @midimap8map midichords chords :chords note vel )
;;                                   ;; (do
;;                                   ;;   (if (= false (.contains @midimap8 note))
;;                                   ;;     (do ;(println midimap8)
;;                                   ;;       (swap! midimap8 conj note)
;;                                   ;;       (reset! midimap8 (vec (sort @midimap8)))
;;                                   ;;       (swap! midimap8map conj {:x (rand-int width) :y (rand-int height) :z (rand-int 500) })
;;                                   ;;       (reset! midichords [{:channel :chords} @midimap8 @midimap8map])
;;                                   ;;       ) )

;;                                   ;;   (swap! chords assoc :note note :velocity vel))
;;                                   (if (= channel 9)
;;                                     (swap! keyz assoc :note note :velocity vel) )))))))))))))
;;           ::keyboard-handler)


(defn midiparse [midimap midimapatom midimapmap midimapmapatom channelmap channelname channelnamekey note vel ]
  (do
    (if (= false (.contains @midimap note))
      (do ;(println midimap8)
        (swap! midimap conj note)
        (reset! midimap (vec (sort @midimap)))
        (swap! midimapmap conj {:x (rand-int width) :y (rand-int height) :z (rand-int 500) })
        (reset! channelmap [{:channel channelnamekey} @midimap @midimapmap])
        ) )

    (swap! channelname assoc :note note :velocity vel)
    ))


(on-event [:midi :note-on]
          (fn [e]
              (let [note (:note e)
                    vel  (:velocity e)
                    channel (:channel e)]
                (case channel
                  0 (midiparse midimap0 @midimap0 midimap0map @midimap0map midibd bd :bd note vel )
                  1 (midiparse midimap1 @midimap1 midimap1map @midimap1map midisd sd :sd note vel )
                  2 (midiparse midimap2 @midimap2 midimap2map @midimap2map midich ch :ch note vel )
                  3 (midiparse midimap3 @midimap3 midimap3map @midimap3map midioh oh :oh note vel )
                  4 (midiparse midimap4 @midimap4 midimap4map @midimap4map midipc1 pc1 :pc1 note vel )
                  5 (midiparse midimap5 @midimap5 midimap5map @midimap5map midipc2 pc2 :pc2 note vel )
                  6 (midiparse midimap6 @midimap6 midimap6map @midimap6map midild1 ld1 :ld1 note vel )
                  7 (midiparse midimap7 @midimap7 midimap7map @midimap7map midild2 ld2 :ld2 note vel )
                  8 (midiparse midimap8 @midimap8 midimap8map @midimap8map midichords chords :chords note vel )
                  9 (midiparse midimap9 @midimap9 midimap9map @midimap9map midikeyz keyz :keyz note vel )
                  15 (if (= note 60) (swap! bbeat inc) (println "ch15 unbeat"))
                  (println "unchannelled midi")
                  )

                )
              )
          ::keyboard-handler)

(on-event [:midi :control-change]
          (fn [e]
            ;(println e)
            (let [data1 (:data1 e)
                  data2 (:data2 e)
                  channel (:channel e)]
              (if (and  (= channel 2) (= data1 10))
                (swap! ch assoc :pan data2)
                )
              )
            )
          ::control-handler
          )
