
// foo.c
#include <stdio.h>
#include "lecture32_Foo.h"



JNIEXPORT void JNICALL Java_lecture32_Foo_bar__Ljava_lang_String_2Ljava_lang_Object_2
  (JNIEnv *env, jobject thisObject, jstring str, jobject obj) {
  jclass cls = (*env)->GetObjectClass(env, thisObject);
  jfieldID fieldID = (*env)->GetFieldID(env, cls, "j", "I");
  if((*env)->ExceptionOccurred(env)) {
    printf("Exception!\n");
    (*env)->ExceptionClear(env);
  }
  fieldID = (*env)->GetFieldID(env, cls, "i", "I");
  jint value = (*env)->GetIntField(env, thisObject, fieldID);
  // we should put an exception guard here as well.
  printf("Hello, World 0x%x\n", value);
  return;
}