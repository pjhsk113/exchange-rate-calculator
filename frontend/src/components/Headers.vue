<template>
  <h1>{{ msg }}</h1>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator'

@Component
export default class Headers extends Vue {
  @Prop() private msg!: string;
}
</script>

<style scoped lang="scss">

</style>
