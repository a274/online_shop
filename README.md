# Приложение под Andriod "интернет-магазин фермерских продуктов"
#### Приложение имеет следующий функционал:
- просмотр фотографий товаров
- сохранение товаров в корзину
- выбор количества покупаемого товара
- поиск продуктов по категориям
- оформление заказа
- общение с продавцом
- просмтор истории заказов
- cохранение личных данных (ФИО, номер телефона, адрес)
- редактирование личных данных


#### Описание зависимостей:
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.google.firebase:firebase-database:19.7.0'
    implementation 'com.firebaseui:firebase-ui:0.6.2'
    implementation 'com.google.firebase:firebase-core:18.0.2'
    implementation 'com.google.firebase:firebase-auth:20.0.3'

    implementation 'com.squareup.retrofit2:retrofit:2.8.1'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'android.arch.persistence.room:runtime:1.1.1'
    annotationProcessor 'android.arch.persistence.room:compiler:1.1.1'
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.squareup.retrofit2:converter-gson:2.8.1'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation "android.arch.persistence.room:runtime:1.1.1"
    annotationProcessor "android.arch.persistence.room:compiler:1.1.1"
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'com.squareup.picasso:picasso:2.5.2'

    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.navigation:navigation-fragment:2.3.4'
    implementation 'androidx.navigation:navigation-ui:2.3.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    
#### Сборка проекта:
Приложение открывается и собирается в среде разработки Android Studio 
и затем устанавливается на физическое устройство либо на эмулятор.

Для установки серверной части необходим работающий сервер. 
В папку,  где содержатся отображаемые в веб браузере файлы, 
необходимо поместить папку серверной части. 
В клиентской части - Android приложении - необходимо прописать базовый адрес сервера, 
который объявляется один раз в файле App.java в строке URL.

