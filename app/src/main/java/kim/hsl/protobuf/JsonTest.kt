package kim.hsl.protobuf

import android.util.Log
import com.alibaba.fastjson.JSON
import com.google.gson.Gson

class JsonTest {

    fun jsonTest(){
        // json 测试

        // 初始化 kim.hsl.protobuf.AddressBook 对象
        var addressBook: AddressBook = AddressBook()

        addressBook.persons = mutableListOf(
            Person("Tom", 0, "", mutableListOf(PhoneNumber("666", PhoneNumber.PhoneType.MOBILE))),
            Person("Jerry", 1, "", mutableListOf(PhoneNumber("888", PhoneNumber.PhoneType.MOBILE)))
        )


        // 测试 fastjson

        var fastjsonStart = System.currentTimeMillis()
        // fastjson 序列化 : 将 addressBook 转为 json 字符串
        var jsonString: String = JSON.toJSONString(addressBook)
        // 将字符串转为 Byte 数组
        var bytes = jsonString.toByteArray()

        Log.i(
            MainActivity.TAG, "fastjson 序列化耗时 ${System.currentTimeMillis() - fastjsonStart} ms , " +
                "序列化大小 ${bytes.size} 字节")

        fastjsonStart = System.currentTimeMillis()
        // 序列化操作
        JSON.parseObject(String(bytes), com.example.tutorial.protos.AddressBook::class.java)

        Log.i(MainActivity.TAG, "fastjson 反序列化耗时 ${System.currentTimeMillis() - fastjsonStart} ms")




        // 测试 gson

        var gsonStart = System.currentTimeMillis()
        // gson 序列化 : 将 addressBook 转为 json 字符串
        jsonString = Gson().toJson(addressBook)
        // 将字符串转为 Byte 数组
        bytes = jsonString.toByteArray()

        Log.i(
            MainActivity.TAG, "gson 序列化耗时 ${System.currentTimeMillis() - gsonStart} ms , " +
                "序列化大小 ${bytes.size} 字节")

        gsonStart = System.currentTimeMillis()
        Gson().fromJson(String(bytes), com.example.tutorial.protos.AddressBook::class.java)
        Log.i(MainActivity.TAG, "gson 反序列化耗时 ${System.currentTimeMillis() - gsonStart} ms")
    }

}

class PhoneNumber{
    enum class PhoneType{
        MOBILE,
        HOME,
        WORK;
    }
    var number: String = ""
    var type: PhoneType = PhoneType.MOBILE

    constructor(number: String, type: PhoneType) {
        this.number = number
        this.type = type
    }
}

class Person{
    lateinit var name: String
    var id: Int = 0
    var email: String = ""
    var phones: MutableList<PhoneNumber> = mutableListOf()

    constructor(name: String, id: Int, email: String, phones: MutableList<PhoneNumber>) {
        this.name = name
        this.id = id
        this.email = email
        this.phones = phones
    }
}

class AddressBook{
    var persons: MutableList<Person> = mutableListOf()
}