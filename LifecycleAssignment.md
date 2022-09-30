

# Activity Lifecycle ve Sorunlardan Kaçınmak
	Bir kullanıcı uygulamanızda gezinirken, uygulamanızdan çıkarken ve uygulamanıza geri dönerken, uygulamanızdaki Activity instanceları lifecyclelardaki farklı durumlar arasında geçiş yapar. Activity sınıfı, Activity’nin bir durumun değiştiğini bilmesini sağlayan bir dizi callback sağlar. (Sistemin bir Activity oluşturması, durdurması veya devam ettirmesi veya Activity’nin içinde bulunduğu süreci yok etmesi vb.)

	Lifecycle callback fonksiyonları içinde, kullanıcı Activity’den ayrılıp tekrar girdiğinde Activity’nizin nasıl davranacağını bildirebilirsiniz. Örneğin, bir video oynatıcı oluşturuyorsanız, kullanıcı başka bir uygulamaya geçtiğinde videoyu duraklatabilir ve ağ bağlantısını sonlandırabilirsiniz. Kullanıcı geri döndüğünde, ağa yeniden bağlanabilir ve kullanıcının videoyu aynı noktadan devam ettirmesine izin verebilirsiniz. Başka bir deyişle, her callback, belirli bir durum değişikliğine uygun olan belirli işleri gerçekleştirmenize izin verir. Doğru zamanda doğru işi yapmak ve geçişleri düzgün bir şekilde ele almak, uygulamanızı daha sağlam ve performanslı hale getirir. Örneğin, lifecycle callbacklerinin iyi bir şekilde uygulanması, uygulamanızın aşağıdakilerden kaçınmasını sağlamaya yardımcı olabilir:

- Kullanıcı, uygulamanızı kullanırken bir telefon araması alırsa veya başka bir uygulamaya geçerse kilitlenmesi
- Kullanıcı aktif olarak kullanmadığında değerli sistem kaynaklarını tüketmek
- Uygulamanızdan ayrılıp daha sonra geri dönerse kullanıcının ilerlemesini kaybetmek
- Ekran yatay ve dikey yön arasında döndüğünde kullanıcının ilerlemesinin çökmesi veya kaybolması

## Lifecycle Callbacks

### onCreate()
	Bir Activity başlatıldığında arka planda devreye giren ilk metottur. Uygulamamız açıldığı zaman gerçekleşmesini istediğimiz yaşam döngüsü içerisinde yalnızda bir kere gerçekleşmesi gereken eylemleri (değişkenleri tanımlamak vs.) onCreate() metodunda yazarız. Bu işlemler uygulama Destroy edilmediği sürece onCreate() metodu tekrar çalışmayacağı için sadece bir kez çalışır.
onCreate() metodu Bundle tipinde bir nesne olan ve Activity’nin kaydedilen son durumunu içeren savedInstanceState parametresini alır. Bu metot içerisinde çağrılan setContentView() de xml dosyası aracılığıyla Activity view’inin çizilmesini sağlar.

### onStart()
	onCreate() metodundan sonra çalışan metottur. Arka planda bekleyen uygulamanın ekrana yeniden getirilmesiyle de çalışır bu metot. Bu metot ile Activity’nin arayüzü kullanıcıya görünür hale gelir.

### onResume()
	onRestoreInstanceState(), onRestart() veya onPause() metotlarından sonra çağrılır. Bu metot içerisine uygulama ön plandayken gerçekleştirmek istediğimiz eylemlerin kodlarını yazabiliriz. Animasyon veya video oynatma, cihaz özelliklerini (mikrofon, kamera vs.) kullanma gibi işlevleri başlatmak için bu metodu kullanabiliriz. Örneğin ekranımızda sürekli dönen bir video olarak, bu video uygulama geri plana atılınca ya da yukarıda bahsettiğimiz durumlar karşısında geri durdurulmalı, Activity tekrar ayağa kalktığında oynatılmalıdır. Yani video onResume() metodunda başlatılıp onPause() ve onStop() metotlarında duraklatılmalıdır.

### onPause()
	Activity arka plana alındığında, arama geldiğinde, başka bir uygulama açıldığında ya da uygulama üzerinde telefondan kaynaklı bir uyarı penceresi vb. diyalog açıldığında uygulamamız duraklatılıp kesintiye uğratıldığında onPause() metodu tetiklenir. Buradan da anlaşılacağı gibi onPause() metodunu uygulamamız çalışırken herhangi bir yerde duraklatıldığı zaman gerçekleştirilmesini istediğimiz işlemlerimizi yaparız.

### onStop()
	onPause() metodundan sonra çalışır. Uygulama artık kullanıcı tarafından görünmüyorsa durdurulma aşamasına girmiş demektir. Yeni bir Activity’nin tam ekran açılması ya da mevcuttaki Activity’nin çalışmayı bitirmesi bu metodun çağrılmasına neden olur. Herhangi bir işlemi uygulama kapanırken sonlandırmak istersek bu işlemleri (animasyonları durdurmak, detaylı (fine-grained) konum takibinden daha az detaylı (coarse-grained) konum takibine geçmek gibi)) burada yapabiliriz. Bellek kullanımını daha verimli hale getirmek için kullanıcının ihtiyaç duymadığı kaynakları onStop() metodunda kapatabiliriz. Bu şekilde CPU’yu da yormamış oluruz.

### onRestart()
	onStop()’tan sonra Activity kullanıcı tarafından yeniden ön plana getirildiğinde çağrılır. Bu metodu onStart() ve onResume() metotları takip eder.

### onDestroy()
	Activity yok edilmeden önce çağrılan metottur. Uygulama içerisinde finish() metodunun çağrılmasıyla ya da Activity uzun süre etkin duruma gelmeyip sistem tarafından otomatik kapatıldığı durumlarda bu metot çağrılır. Uygulamanın hangi sebeble sonlandığını isFinishing metodunu kullanarak öğrenebiliriz.



## ANR(Application Not Responding) Hatası Nedir ve Nasıl Kaçınılır?
	ANR, Uygulama Yanıt Vermiyor anlamına gelir. UI güncellemekten sorumlu olan Main Thread’inde, genellikle yaklaşık 5 saniye olmak üzere uzun süren bir işlem yürütüyorsanız bir ANR oluşur. Bu noktada, GUI (Grafik Kullanıcı Arayüzü) kilitlenecek ve kullanıcının bastığı herhangi bir şeyle sonuçlanabilecek herhangi bir işlem yapılamayacaktır. Yaklaşık 5 saniye geçtikten sonra, thread hala düzelmediyse, kullanıcıya cihazın yanıt vermediğini bildiren bir ANR iletişim kutusu gösterilir ve kullanıcıya uygulamanın yanıt vermesi umuduyla bekleme veya force quit seçeneği verir.

	Diyelim ki, bir EditText bileşeninin metnini ayarlamaya çalışıyorsunuz ancak EditText Null ise ve Exception’ı yakalamak için bir try-catch ifadesi yoksa, uygulamanız çökebilir ve zorla kapatılabilir. Kullanıcı çökmeye neyin sebep olduğunu görmez. Uygulamanın beklenmedik bir şekilde kapanmaya zorlandığını belirten bir diyalog gösterilir ve onlara bir hata raporu gönderme seçeneği sunar.

	Main Thread’de ağır işler yapmak da hatalı bir kullanımdır. Bunun yerine IntentService, AsyncTask Handler veya bir başka Thread kullanılabilir.

---
>Emre Tonkal
---

